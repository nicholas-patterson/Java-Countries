package com.nicholas.javacountries.repositories;

import com.nicholas.javacountries.models.Country;
import org.springframework.data.repository.CrudRepository;

public interface CountryRepository extends CrudRepository<Country, Long> {
}
