package com.example.ipchallengemeli.components;

import com.example.ipchallengemeli.domain.dto.CountryInfo;
import com.example.ipchallengemeli.domain.dto.CountryIpInfo;
import com.example.ipchallengemeli.domain.response.CountryDistance;
import com.example.ipchallengemeli.util.StringFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class StatisticsComponent {

    private final ConcurrentHashMap<String, CountryDistance> countryCodeToDistance;

    @Autowired
    private StringFormat stringFormat;

    public StatisticsComponent() {
        this.countryCodeToDistance = new ConcurrentHashMap<>();
    }


    public void populate(CountryIpInfo countryByIp, CountryInfo infoByCountryCode) {
        CountryDistance countryDistance = countryCodeToDistance.get(countryByIp.getCountryCode());
        if (countryDistance == null) {
            countryDistance = new CountryDistance(countryByIp.getCountryName(), stringFormat.getDistance(infoByCountryCode.getLatlng()));
            countryCodeToDistance.put(countryByIp.getCountryCode(), countryDistance);
        } else {
            countryDistance.getInvocations().incrementAndGet();
        }
    }

    public CountryDistance getFarthestDistanceToBsAs() {
        Optional<Entry<String, CountryDistance>> max = countryCodeToDistance.entrySet()
                .stream().max(Entry.comparingByValue(Comparator.comparing(CountryDistance::getDistanceKms)));
        if (max.isPresent()) {
            return max.get().getValue();
        }
        return null;

    }

    public CountryDistance getClosestDistanceToBsAs() {
        Optional<Entry<String, CountryDistance>> min = countryCodeToDistance.entrySet()
                .stream().min(Entry.comparingByValue(Comparator.comparing(CountryDistance::getDistanceKms)));
        if (min.isPresent()) {
            return min.get().getValue();
        }
        return null;
    }

    public long getAllExecutionsAverage() {
        long total = 0;
        for (Entry<String, CountryDistance> entry : countryCodeToDistance.entrySet()) {
            CountryDistance value = entry.getValue();
            total = value.getDistanceKms() * value.getInvocations().get();
        }
        return total;
    }
}
