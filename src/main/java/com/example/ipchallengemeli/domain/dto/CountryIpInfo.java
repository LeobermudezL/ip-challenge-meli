package com.example.ipchallengemeli.domain.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class CountryIpInfo implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private String countryCode;
	private String countryName;
}
