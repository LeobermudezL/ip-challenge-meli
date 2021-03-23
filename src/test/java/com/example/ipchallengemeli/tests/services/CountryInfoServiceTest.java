package com.example.ipchallengemeli.tests.services;

import com.example.ipchallengemeli.domain.country.CountryInfoService;
import com.example.ipchallengemeli.domain.dto.CountryExchange;
import com.example.ipchallengemeli.domain.dto.CountryInfo;
import com.example.ipchallengemeli.domain.dto.CountryIpInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class CountryInfoServiceTest {

    @Autowired
    CountryInfoService countryInfoService;

    @Test
    public void obtenerPaisPorIp(){
        CountryIpInfo countryIpInfo = countryInfoService.getCountryByIp("1.123.30.170");
        assertNotNull(countryIpInfo);
        assertEquals("AU", countryIpInfo.getCountryCode());
        assertEquals("Australia", countryIpInfo.getCountryName());
   }
    @Test
    public void getCountryInfo() {

        CountryInfo countryInfo = countryInfoService.getInfoByCountryCode("AU");
        assertNotNull(countryInfo);

        assertEquals("en", countryInfo.getLanguages().get(0).getIso639_1());
        assertEquals("English", countryInfo.getLanguages().get(0).getName());

        assertEquals("UTC+05:00", countryInfo.getTimezones().get(0));

        assertEquals(-27, countryInfo.getLatlng().get(0));
        assertEquals(133, countryInfo.getLatlng().get(1));

        assertEquals("AUD", countryInfo.getCurrencies().get(0).getCode());
        assertEquals("Australian dollar", countryInfo.getCurrencies().get(0).getName());
    }

    @Test
    public void getCountryExchange() {
        CountryExchange countryExchange = countryInfoService.getCountryExchange("EUR", "USD");
        assertNotNull(countryExchange);
        Map<String, Double> rates = countryExchange.getRates();
        assertNotNull(rates);
        assertNotNull(rates.get("USD"));
    }
}
