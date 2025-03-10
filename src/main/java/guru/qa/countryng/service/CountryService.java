package guru.qa.countryng.service;

import guru.qa.countryng.data.Country;
import guru.qa.countryng.data.CountryEntity;
import guru.qa.countryng.data.CountryRepository;
import jakarta.annotation.Nonnull;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CountryService {

    private final CountryRepository countryRepository;

    @Autowired
    public CountryService(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    public List<Country> getAllCountries() {
        return countryRepository.findAll()
                .stream()
                .map(Country::fromEntity)
                .toList();
    }

    @Transactional
    public @Nonnull Country saveCountry(@Nonnull Country country) {
        return Country.fromEntity(countryRepository.save(country.toEntity()));
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
}
