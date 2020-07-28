package com.nicholas.javacountries.controllers;

import com.nicholas.javacountries.models.Country;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import com.nicholas.javacountries.repositories.CountryRepository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@RestController



public class CountryController {

    @Autowired
    CountryRepository countryRepository;


    @GetMapping(value = "/names/all", produces = {"application/json"})
    public ResponseEntity<?> getCountries(){
        List<Country> countries = new ArrayList<>();

        countryRepository.findAll().iterator().forEachRemaining(countries::add);

        return new ResponseEntity<>(countries, HttpStatus.OK);
    }


    @GetMapping(value = "/names/start/{letter}", produces = {"application/json"})
    public ResponseEntity<?> getCountriesStartWithU(@PathVariable char letter){
        List<Country> countries = new ArrayList<>();

        countryRepository.findAll().iterator().forEachRemaining(countries::add);

       List<Country> returnedCountries =  countries.stream().filter(country -> country.getName().toLowerCase().charAt(0) == letter).collect(Collectors.toList());

        return new ResponseEntity<>(returnedCountries, HttpStatus.OK);
    }

    @GetMapping(value = "/population/total", produces = {"application/json"})
    public ResponseEntity<?> getTotalPopulation(){

        List<Country> countries = new ArrayList<>();

        countryRepository.findAll().iterator().forEachRemaining(countries::add);

        long populationTotal = countries.stream().map(Country::getPopulation).reduce(0L, (Long a, Long b) -> a + b);

        System.out.println("The total population is " + populationTotal);

        return new ResponseEntity<>(HttpStatus.OK);

    }

    @GetMapping(value = "/population/min", produces = {"application/json"})
    public ResponseEntity<?> getMinimumPopulation(){
        List<Country> countries = new ArrayList<>();

        countryRepository.findAll().iterator().forEachRemaining(countries::add);

        Country minCountry = Collections.min(countries, Comparator.comparing(Country::getPopulation));

        return new ResponseEntity<>(minCountry, HttpStatus.OK);
    }

    @GetMapping(value = "/population/max", produces = {"application/json"})
    public ResponseEntity<?> getMaximumPopulation(){
        List<Country> countries = new ArrayList<>();

        countryRepository.findAll().iterator().forEachRemaining(countries::add);

        Country maxCountry = Collections.max(countries, Comparator.comparing(Country::getPopulation));

        return new ResponseEntity<>(maxCountry, HttpStatus.OK);
    }
}
