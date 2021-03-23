package com.example.ipchallengemeli.domain.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class CountryCurrency implements Serializable {

	private static final long serialVersionUID = 1L;

	private String code;
	private String name;
}
