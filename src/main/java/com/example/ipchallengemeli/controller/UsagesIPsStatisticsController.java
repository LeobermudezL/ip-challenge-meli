package com.example.ipchallengemeli.controller;

import com.example.ipchallengemeli.components.StatisticsComponent;
import com.example.ipchallengemeli.domain.response.CountryDistance;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = {"Estadisticas de uso de los paises"})
@RestController
@RequestMapping("/usage")
public class UsagesIPsStatisticsController {

    @Autowired
    private StatisticsComponent stats;

    private static final Logger logger = LoggerFactory.getLogger(UsagesIPsStatisticsController.class);

    @Cacheable("DistanciaMasLejana")
    @ApiOperation(value = "Distancia más lejana a Buenos Aires")
    @GetMapping("/distance/farthest")
    public ResponseEntity<CountryDistance> getDistinciaMasLejanaABsAs() {
        logger.info("Informacion de la distincia mas larga {}", stats.getFarthestDistanceToBsAs());
        return new ResponseEntity<>(stats.getFarthestDistanceToBsAs(), HttpStatus.OK);
    }

    @Cacheable("DistanciaMasCercana")
    @ApiOperation(value = "Distancia más cercana a Buenos Aires")
    @GetMapping("/distance/closest")
    public ResponseEntity<CountryDistance> getDistanciaMasCercanaABsAs() {
        logger.info("Informacion de la distancia mas cercana a Buenos Aires {}", stats.getClosestDistanceToBsAs());
        return new ResponseEntity<>(stats.getClosestDistanceToBsAs(), HttpStatus.OK);
    }

    @Cacheable("DistanciaPromedio")
    @ApiOperation(value = "Distancia promedio de todas las busquedas")
    @GetMapping("/distance/average")
    public ResponseEntity<Long> getPromedioDeTodasLasBusquedas() {
        logger.info("Informacion de la distancia promedio de las busquedas {}", stats.getAllExecutionsAverage());
        return new ResponseEntity<>(stats.getAllExecutionsAverage(), HttpStatus.OK);
    }
}
