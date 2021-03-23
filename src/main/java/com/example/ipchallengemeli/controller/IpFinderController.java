package com.example.ipchallengemeli.controller;

import com.example.ipchallengemeli.domain.country.CountryInfoService;
import com.example.ipchallengemeli.exceptions.BadRequestIpException;
import org.apache.commons.validator.routines.InetAddressValidator;
import com.example.ipchallengemeli.domain.country.CountryInfoFinderService;
import com.example.ipchallengemeli.domain.response.CountryIpRelatedInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@Api(tags = {"IP Information"})
@RestController
@RequestMapping("/ipinfo")
public class IpFinderController {
    @Autowired
    private CountryInfoFinderService finder;

    private static final Logger logger = LoggerFactory.getLogger(IpFinderController.class);

    @ApiOperation(value = "Información general de país dada una IP")
    @GetMapping
    public ResponseEntity<CountryIpRelatedInfo> getInformacionDePaisPorIp(@RequestParam(required = true) String ip) throws BadRequestIpException {

        if (!InetAddressValidator.getInstance().isValidInet4Address(ip)) {
            throw new BadRequestIpException(ip);
        }

        CountryIpRelatedInfo infoByIp = finder.getInfoByIp(ip);
        logger.info("Respuesta de informacion general de un pais por IP {}", infoByIp);
        return new ResponseEntity<>(infoByIp, HttpStatus.OK);
    }
}
