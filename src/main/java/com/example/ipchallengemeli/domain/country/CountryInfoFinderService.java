package com.example.ipchallengemeli.domain.country;

import com.example.ipchallengemeli.components.StatisticsComponent;
import com.example.ipchallengemeli.domain.dto.CountryCurrency;
import com.example.ipchallengemeli.domain.dto.CountryExchange;
import com.example.ipchallengemeli.domain.dto.CountryInfo;
import com.example.ipchallengemeli.domain.dto.CountryIpInfo;
import com.example.ipchallengemeli.domain.response.CountryIpRelatedInfo;
import com.example.ipchallengemeli.util.StringFormat;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpStatusCodeException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Iterator;
import java.util.LinkedList;


@Service
public class CountryInfoFinderService {

    @Value("${app.currency.symbol}")
    private String currencySymbol;

    @Autowired
    private CountryInfoService infoService;

    @Autowired
    private StatisticsComponent statisticsComponent;

    @Autowired
    private StringFormat stringFormat;

    private static final Logger logger = LoggerFactory.getLogger(CountryInfoFinderService.class);

    @Cacheable("InfoByIp")
    public CountryIpRelatedInfo getInfoByIp(String ip) {
        CountryIpRelatedInfo info = new CountryIpRelatedInfo();

        CountryIpInfo countryByIp = infoService.getCountryByIp(ip);
        CountryInfo infoByCountryCode = infoService.getInfoByCountryCode(countryByIp.getCountryCode());

        info.setIp(ip);
        info.setLocalDateTime(LocalDateTime.now()
                .format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss")));
        info.setCountry(countryByIp.getCountryName());
        info.setIsoCode(countryByIp.getCountryCode());
        info.setTime(stringFormat.getFormattedTime(infoByCountryCode.getTimezones()));
        info.setLanguage(stringFormat.getFormattedLanguages(infoByCountryCode.getLanguages()));
        info.setCurrency(formatCurrencies(infoByCountryCode.getCurrencies()));
        info.setDistance(stringFormat.getFormattedDistance(infoByCountryCode.getLatlng()));

        statisticsComponent.populate(countryByIp, infoByCountryCode);
        logger.info("Este es la info por IP solicitada {}",info);
        return info;
    }

    public String formatCurrencies(LinkedList<CountryCurrency> currencies) {
        StringBuilder builder = new StringBuilder();
        for (Iterator<CountryCurrency> iterator = currencies.iterator();
             iterator.hasNext(); ) {

            CountryCurrency currency = iterator.next();
            CountryExchange exchange;
            try {
                exchange = infoService.getCountryExchange(currency.getCode(), currencySymbol);
                builder.append(currency.getCode()).append(" (1 ").append(currency.getCode())
                        .append(" = ").append(exchange.getRates().get(currencySymbol))
                        .append(" ").append(currencySymbol).append(")");
            } catch (HttpStatusCodeException ex) {
                String error = new JSONObject(ex.getResponseBodyAsString()).getString("error");
                builder.append(currency.getCode()).append(" (").append(error).append(")");
            }

            if (iterator.hasNext()) {
                builder.append(", ");
            }

        }
        return builder.toString();
    }
}
