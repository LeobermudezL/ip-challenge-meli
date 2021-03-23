package com.example.ipchallengemeli.domain.response;

import lombok.Data;

@Data
public class CountryIpRelatedInfo {

	private String ip;
	private String localDateTime;
	private String country;
	private String isoCode;
	private String language;
	private String currency;
	private String time;
	private String distance;
}
