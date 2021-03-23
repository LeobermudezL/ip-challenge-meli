package com.example.ipchallengemeli.domain.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class CountryLanguage implements Serializable {

	private static final long serialVersionUID = 1L;

	private String iso639_1;
	private String name;
}
