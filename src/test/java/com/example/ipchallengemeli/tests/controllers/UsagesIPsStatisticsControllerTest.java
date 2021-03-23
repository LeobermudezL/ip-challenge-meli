package com.example.ipchallengemeli.tests.controllers;

import com.example.ipchallengemeli.components.StatisticsComponent;
import com.example.ipchallengemeli.controller.IpFinderController;
import com.example.ipchallengemeli.controller.UsagesIPsStatisticsController;
import com.example.ipchallengemeli.domain.country.CountryInfoFinderService;
import com.example.ipchallengemeli.domain.response.CountryDistance;
import com.example.ipchallengemeli.domain.response.CountryIpRelatedInfo;
import okhttp3.Response;
import org.checkerframework.checker.units.qual.C;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.nio.file.AtomicMoveNotSupportedException;
import java.util.concurrent.atomic.AtomicLong;

import static org.mockito.Mockito.when;

public class UsagesIPsStatisticsControllerTest {

    @InjectMocks
    UsagesIPsStatisticsController usagesIPsStatisticsController;

    @Mock
    StatisticsComponent stats;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    private static final String IP = "1.123.30.170";

    @Test
    public void distanciaMasCernanaTest(){
        when(stats.getClosestDistanceToBsAs()).thenReturn(countryDistanceMock());
        ResponseEntity<CountryDistance> respuesta = usagesIPsStatisticsController.getDistanciaMasCercanaABsAs();
        Assert.assertNotNull(respuesta);
    }
    @Test
    public void distanciaMasLejosTest(){
        CountryDistance countryDistance = countryDistanceMock();
        countryDistance.setDistanceKms(12999);
        countryDistance.setCountryName("Australia");
        countryDistance.setInvocations(new AtomicLong());
        when(stats.getFarthestDistanceToBsAs()).thenReturn(countryDistance);
        ResponseEntity<CountryDistance> respuesta = usagesIPsStatisticsController.getDistinciaMasLejanaABsAs();
        Assert.assertNotNull(respuesta);
    }

    @Test
    public void distanciaPromedioTest(){
        long num = 1;
        when(stats.getAllExecutionsAverage()).thenReturn(num);
        ResponseEntity<Long> respuesta = usagesIPsStatisticsController.getPromedioDeTodasLasBusquedas();
        Assert.assertNotNull(respuesta);
    }
    private CountryDistance countryDistanceMock(){
        CountryDistance countryDistance = new CountryDistance();
        countryDistance.setCountryName("Argentina");
        countryDistance.setDistanceKms(0);
        countryDistance.setInvocations(new AtomicLong());
        return countryDistance;
    }
}
