package com.example.ipchallengemeli.domain.response;

import lombok.Data;

import java.util.concurrent.atomic.AtomicLong;

@Data
public class CountryDistance {

	private String countryName;
	private long distanceKms;
	private AtomicLong invocations; 
	
	public CountryDistance(String countryName, long distanceKms) {
		this.countryName = countryName;
		this.distanceKms = distanceKms;
		this.invocations = new AtomicLong(1);
	}
}
