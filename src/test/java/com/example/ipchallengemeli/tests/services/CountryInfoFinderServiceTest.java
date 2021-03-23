//package com.example.ipchallengemeli.tests.services;
//
//import com.example.ipchallengemeli.components.StatisticsComponent;
//import com.example.ipchallengemeli.domain.country.CountryInfoFinderService;
//import com.example.ipchallengemeli.domain.country.CountryInfoService;
//import com.example.ipchallengemeli.domain.dto.CountryCurrency;
//import com.example.ipchallengemeli.domain.dto.CountryInfo;
//import com.example.ipchallengemeli.domain.dto.CountryIpInfo;
//import com.example.ipchallengemeli.domain.dto.CountryLanguage;
//import com.example.ipchallengemeli.util.StringFormat;
//import org.junit.Before;
//import org.junit.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.Mockito;
//import org.mockito.MockitoAnnotations;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.client.RestTemplate;
//import static org.mockito.Mockito.mock;
//
//import java.util.LinkedList;
//
//import static org.mockito.Mockito.when;
//
//public class CountryInfoFinderServiceTest {
//
//    @InjectMocks
//    CountryInfoFinderService countryInfoFinderService;
//
//    @Mock
//    CountryInfoFinderService countryInfoFinderService2;
//
//    @Mock
//    CountryInfoService countryInfoService;
//
//    @Mock
//    StringFormat stringFormat;
//
//    @Mock
//    StatisticsComponent statisticsComponent;
//
//    @Mock
//    RestTemplate restTemplate;
//
//
//    private static final String IP = "1.123.30.170";
//    @Before
//    public void init() {
//        MockitoAnnotations.initMocks(this);
//    }
//
//    @Test
//    public void obtenerInfoPaisPorIp(){
//        when(countryInfoService.getCountryByIp(Mockito.any(String.class))).thenReturn(countryIpInfoMock());
//        when(countryInfoFinderService2.formatCurrencies(Mockito.any(LinkedList.class))).thenReturn("1");
//        when(countryInfoService.getInfoByCountryCode(Mockito.any(String.class))).thenReturn(countryInfoMock());
//        when(stringFormat.getFormattedTime(Mockito.any(LinkedList.class))).thenReturn("1");
//        when(stringFormat.getFormattedLanguages(Mockito.any(LinkedList.class))).thenReturn("1");
//        when(stringFormat.getFormattedDistance(Mockito.any())).thenReturn("1");
//        when(stringFormat.getDistance(Mockito.any(LinkedList.class))).thenReturn(1);
//        statisticsComponent.populate(Mockito.any(CountryIpInfo.class),Mockito.any(CountryInfo.class));
//        countryInfoFinderService.getInfoByIp(IP);
//
//
//
//    }
//    private CountryIpInfo countryIpInfoMock(){
//        CountryIpInfo countryIpInfo = new CountryIpInfo();
//        countryIpInfo.setCountryCode("AUS");
//        countryIpInfo.setCountryName("Australia");
//        return countryIpInfo;
//    }
//    private CountryInfo countryInfoMock(){
//        CountryInfo countryInfo = new CountryInfo();
//        CountryCurrency countryCurrency = new CountryCurrency();
//        countryCurrency.setCode("AUS");
//        countryCurrency.setCode("Australia");
//        LinkedList<CountryCurrency> lista = new LinkedList<>();
//        lista.add(0,countryCurrency);
//        LinkedList<CountryLanguage> listaLanguages = new LinkedList<>();
//        CountryLanguage countryLanguage = new CountryLanguage();
//        countryLanguage.setName("English");
//        countryLanguage.setIso639_1("en");
//        listaLanguages.add(0,countryLanguage);
//        countryInfo.setCurrencies(lista);
//        countryInfo.setLanguages(listaLanguages);
//        LinkedList<String> listaTimeZones = new LinkedList<>();
//        listaTimeZones.add("UTC+05:00");
//        countryInfo.setTimezones(listaTimeZones);
//        LinkedList<Integer>listaIng = new LinkedList<>();
//        listaIng.add(1);
//        countryInfo.setLatlng(listaIng);
//        return countryInfo;
//    }
//}
