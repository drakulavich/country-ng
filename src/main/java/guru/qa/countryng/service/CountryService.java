package guru.qa.countryng.service;

import guru.qa.countryng.data.Country;
import guru.qa.countryng.data.CountryEntity;
import guru.qa.countryng.data.CountryRepository;
import guru.qa.countryng.data.graphql.CountryGql;
import guru.qa.countryng.data.graphql.CountryInputGql;
import jakarta.annotation.Nonnull;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.time.LocalDateTime;
import org.springframework.data.domain.Sort;
import java.util.stream.Collectors;

@Service
public class CountryService {

    private final CountryRepository countryRepository;

    @Autowired
    public CountryService(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    public List<Country> getAllCountries() {
        return countryRepository.findAll(Sort.by(Sort.Direction.DESC, "lastModifyDate"))
            .stream()
            .map(Country::fromEntity)
            .collect(Collectors.toList());
    }

    public List<CountryGql> getAllCountriesGql() {
        return countryRepository.findAll(Sort.by(Sort.Direction.DESC, "lastModifyDate"))
                .stream()
                .map(CountryGql::fromEntity)
                .collect(Collectors.toList());
    }

    @Transactional
    public @Nonnull Country saveCountry(@Nonnull Country country) {
        return Country.fromEntity(countryRepository.save(country.toEntity()));
    }

    @Transactional
    public @Nonnull CountryGql saveCountry(@Nonnull CountryInputGql country) {
        return CountryGql.fromEntity(countryRepository.save(country.toEntity()));
    }

    @Transactional
    public @Nonnull Country updateCountryName(@Nonnull String currentCountryName, @Nonnull String newCountryName) {
        CountryEntity country = countryRepository.findByCountryName(currentCountryName);
        if (country == null) {
            throw new IllegalArgumentException(String.format("Country with name %s not found", currentCountryName));
        }
        country.setCountryName(newCountryName);

        return Country.fromEntity(countryRepository.save(country));
    }

    @Transactional
    public @Nonnull CountryGql updateCountryNameGql(@Nonnull String currentCountryName, @Nonnull String newCountryName) {
        CountryEntity country = countryRepository.findByCountryName(currentCountryName);
        if (country == null) {
            throw new IllegalArgumentException(String.format("Country with name %s not found", currentCountryName));
        }
        country.setCountryName(newCountryName);

        return CountryGql.fromEntity(countryRepository.save(country));
    }
}