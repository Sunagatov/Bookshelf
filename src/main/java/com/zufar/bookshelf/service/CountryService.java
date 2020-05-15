package com.zufar.bookshelf.service;

import com.zufar.bookshelf.entity.Country;
import com.zufar.bookshelf.repository.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CountryService {

    private final CountryRepository countryRepository;

    @Autowired
    public CountryService(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    public void save(Country country){
        countryRepository.save(country);
    }

    public void delete(Country country) {
        countryRepository.delete(country);
    }

    public void update(Country country) {
        countryRepository.save(country);
    }

    public Country get(Long id) {
        return countryRepository.getOne(id);
    }

    public List<Country> getAll() {
        return countryRepository.findAll();
    }
}