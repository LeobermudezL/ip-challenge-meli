package com.example.ipchallengemeli.tests.controllers;


import com.example.ipchallengemeli.controller.IpFinderController;
import com.example.ipchallengemeli.domain.country.CountryInfoFinderService;
import com.example.ipchallengemeli.domain.response.CountryIpRelatedInfo;
import com.example.ipchallengemeli.exceptions.BadRequestIpException;
import org.apache.commons.validator.routines.InetAddressValidator;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;
import org.junit.Assert;
import static org.mockito.Mockito.when;

public class IpFinderControllerTest {
    @InjectMocks
    IpFinderController ipFinderController;

    InetAddressValidator validator;

    @Mock
    CountryInfoFinderService countryInfoFinderService;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    private static final String IP = "1.123.30.170";

    @Test
    public void findIpTest() throws BadRequestIpException {
        when(countryInfoFinderService.getInfoByIp(Mockito.any(String.class))).thenReturn(countryMock());
        ResponseEntity<CountryIpRelatedInfo> respuesta = ipFinderController.getInformacionDePaisPorIp(IP);
        Assert.assertNotNull(respuesta);
    }
    @Test(expected = BadRequestIpException.class)
    public void badRequestTest() throws BadRequestIpException {
        ipFinderController.getInformacionDePaisPorIp("123.30.170");
    }

    private CountryIpRelatedInfo countryMock(){
        CountryIpRelatedInfo countryIpRelatedInfo = new CountryIpRelatedInfo();
        countryIpRelatedInfo.setCountry("Australia");
        countryIpRelatedInfo.setCurrency("AUD (1 AUD = 0.7744155844 USD)");
        countryIpRelatedInfo.setIsoCode("AU");
        countryIpRelatedInfo.setIp("1.123.30.170");
        countryIpRelatedInfo.setLanguage("English (en");
        countryIpRelatedInfo.setLocalDateTime("23-03-2021 01:50:05");
        countryIpRelatedInfo.setTime("06:50:05 (UTC+05:00) o 08:20:05 (UTC+06:30) o 08:50:05 (UTC+07:00) o 09:50:05 (UTC+08:00) o 11:20:05 (UTC+09:30) o 11:50:05 (UTC+10:00) o 12:20:05 (UTC+10:30) o 13:20:05 (UTC+11:30");
        countryIpRelatedInfo.setDistance("12999 kms (-34, -64) a (-27, 133)");
        return countryIpRelatedInfo;
    }
}
