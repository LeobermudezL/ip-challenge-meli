package com.example.ipchallengemeli.domain.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.LinkedList;

@Data
public class CountryInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    private LinkedList<CountryLanguage> languages;
    private LinkedList<String> timezones;
    private LinkedList<Integer> latlng;
    private LinkedList<CountryCurrency> currencies;

}
