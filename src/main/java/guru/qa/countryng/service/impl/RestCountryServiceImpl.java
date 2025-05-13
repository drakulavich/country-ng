package guru.qa.countryng.service.impl;

import guru.qa.countryng.data.Country;
import guru.qa.countryng.data.CountryEntity;
import guru.qa.countryng.data.CountryRepository;
import guru.qa.countryng.service.ICountryService;
import jakarta.annotation.Nonnull;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RestCountryServiceImpl implements ICountryService<Country, Country> {

    private final CountryRepository countryRepository;

    @Autowired
    public RestCountryServiceImpl(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    @Override
    public List<Country> getAllCountries() {
        return countryRepository.findAll(Sort.by(Sort.Direction.DESC, "lastModifyDate"))
                .stream()
                .map(Country::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public @Nonnull Country saveCountry(@Nonnull Country country) {
        return Country.fromEntity(countryRepository.save(country.toEntity()));
    }

    @Override
    @Transactional
    public @Nonnull Country updateCountryName(@Nonnull String countryCode, @Nonnull String newCountryName) {
        CountryEntity country = countryRepository.findByCountryCode(countryCode);
        if (country == null) {
            throw new IllegalArgumentException(String.format("Country with code %s not found", countryCode));
        }
        country.setCountryName(newCountryName);

        return Country.fromEntity(countryRepository.save(country));
    }
}