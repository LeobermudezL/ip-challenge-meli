package com.example.ipchallengemeli.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class BadRequestIpException extends Exception{

    private static final long serialVersionUID = 1L;

    public BadRequestIpException(String ip) {
        super(ip + " formato de ip invalida");
    }

}
