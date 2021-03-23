package com.example.ipchallengemeli.util;

import com.example.ipchallengemeli.domain.dto.CountryLanguage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Iterator;
import java.util.LinkedList;

@Service
public class StringFormat {

    @Value("${latitude.argentina}")
    private int arLatitude;
    @Value("${longitude.argentina}")
    private int arLongitude;

    public String getFormattedLanguages(LinkedList<CountryLanguage> languages) {
        StringBuilder builder = new StringBuilder();
        for (Iterator<CountryLanguage> iterator = languages.iterator();
             iterator.hasNext(); ) {

            CountryLanguage lang = iterator.next();
            builder.append(lang.getName()).append(" (").append(lang.getIso639_1()).append(")");

            if (iterator.hasNext()) {
                builder.append(", ");
            }
        }
        return builder.toString();
    }

    public String getFormattedDistance(LinkedList<Integer> latlng) {
        String formattedDistance = "";
        int distance = getDistance(latlng);
        formattedDistance = distance + " kms " + "(" + arLatitude + ", " + arLongitude + ") a (" + latlng.getFirst() + ", " + latlng.getLast() + ")";
        return formattedDistance;
    }

    public int getDistance(LinkedList<Integer> latlng) {
        return CommonUtil.distance(arLatitude, latlng.getFirst(), arLongitude, latlng.getLast());
    }

    public String getFormattedTime(LinkedList<String> timezones) {
        StringBuilder builder = new StringBuilder();
        for (Iterator<String> iterator = timezones.iterator();
             iterator.hasNext(); ) {

            String timezone = iterator.next();
            builder.append(LocalTime.now(ZoneId.of(timezone)).format(DateTimeFormatter.ofPattern("HH:mm:ss")))
                    .append(" (").append(timezone).append(")");

            if (iterator.hasNext()) {
                builder.append(" o ");
            }
        }
        return builder.toString();
    }
}
