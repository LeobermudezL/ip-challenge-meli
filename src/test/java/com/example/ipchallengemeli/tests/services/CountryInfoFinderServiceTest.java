package com.example.ipchallengemeli.tests.services;

import com.example.ipchallengemeli.domain.country.CountryInfoFinderService;
import com.example.ipchallengemeli.domain.dto.*;
import com.example.ipchallengemeli.domain.response.CountryIpRelatedInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import static org.junit.Assert.assertNotNull;
import java.util.LinkedList;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class CountryInfoFinderServiceTest {

    private static final String IP = "1.123.30.170";
    @Autowired
    CountryInfoFinderService CountryInfoFinderService;

    @Test
    public void obtenerInfoPaisPorIp(){
        CountryIpRelatedInfo ipInformation = CountryInfoFinderService.getInfoByIp(IP);
        assertNotNull(ipInformation);
    }

    @Test
    public void getfotmatcash() {
        String currencies;
        currencies = CountryInfoFinderService.formatCurrencies(infoPaisPorIpMock().getCurrencies());
        assertNotNull(currencies);
    }
    public CountryInfo infoPaisPorIpMock(){
        CountryInfo countryIpRelatedInfo = new CountryInfo();
        LinkedList<String> timezones = new LinkedList<>();
        timezones.add("UTC+05:00");
        LinkedList<Integer> latlng = new LinkedList<>();
        latlng.add(-27);
        latlng.add(133);
        CountryCurrency exchange = new CountryCurrency();
        exchange.setCode("AUD");
        exchange.setName("Australian dollar");
        LinkedList<CountryCurrency> currencyList = new LinkedList<>();
        currencyList.add(exchange);
        CountryLanguage language = new CountryLanguage();
        language.setIso639_1("en");
        language.setName("English");
        LinkedList<CountryLanguage> languagesList = new LinkedList<>();
        languagesList.add(language);
        countryIpRelatedInfo.setLatlng(latlng);
        countryIpRelatedInfo.setLanguages(languagesList);
        countryIpRelatedInfo.setTimezones(timezones);
        countryIpRelatedInfo.setCurrencies(currencyList);
        return countryIpRelatedInfo;
    }
}
