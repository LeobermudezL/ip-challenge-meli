package com.example.ipchallengemeli.domain.country;

import com.example.ipchallengemeli.domain.dto.CountryExchange;
import com.example.ipchallengemeli.domain.dto.CountryInfo;
import com.example.ipchallengemeli.domain.dto.CountryIpInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CountryInfoService {

    @Value("${app.external.api.ip2country.url}")
    private String ip2CountryUrl;
    @Value("${app.external.api.restcountries.url}")
    private String restCountriesUrl;
    @Value("${app.external.api.exchangeratesapi.url}")
    private String exchangeRatesApiUrl;

    @Autowired
    private RestTemplate restTemplate;

    private static final Logger logger = LoggerFactory.getLogger(CountryInfoService.class);

    public CountryIpInfo getCountryByIp(String ip) {
        CountryIpInfo countryIpInfo;
        String url = ip2CountryUrl + "?" + ip;
        countryIpInfo = restTemplate.getForObject(url, CountryIpInfo.class);
        logger.info("Este es informacion del pais que busca {}", countryIpInfo);
        return countryIpInfo;
    }

    public CountryInfo getInfoByCountryCode(String countryCode) {
        CountryInfo countryInfo;
        String url = restCountriesUrl + "/" + countryCode;
        countryInfo = restTemplate.getForObject(url, CountryInfo.class);
        logger.info("Esta es la informacion del pais por codigo {}", countryInfo);
        return countryInfo;
    }

    public CountryExchange getCountryExchange(String baseCurrency, String currentCurrency) {
        CountryExchange countryExchange;
        String url = exchangeRatesApiUrl + "?symbols=" + currentCurrency + "&base=" + baseCurrency;
        countryExchange = restTemplate.getForObject(url, CountryExchange.class);
        logger.info("Esta es la informacion de las monedas del pais buscado {}", countryExchange);
        return countryExchange;
    }

}
