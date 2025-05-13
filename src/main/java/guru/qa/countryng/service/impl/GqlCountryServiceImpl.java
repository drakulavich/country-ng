package guru.qa.countryng.service.impl;

import guru.qa.countryng.data.CountryEntity;
import guru.qa.countryng.data.CountryRepository;
import guru.qa.countryng.data.graphql.CountryGql;
import guru.qa.countryng.data.graphql.CountryInputGql;
import guru.qa.countryng.service.ICountryService;
import jakarta.annotation.Nonnull;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GqlCountryServiceImpl implements ICountryService<CountryGql, CountryInputGql> {

    private final CountryRepository countryRepository;

    @Autowired
    public GqlCountryServiceImpl(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    @Override
    public List<CountryGql> getAllCountries() {
        return countryRepository.findAll(Sort.by(Sort.Direction.DESC, "lastModifyDate"))
                .stream()
                .map(CountryGql::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public @Nonnull CountryGql saveCountry(@Nonnull CountryInputGql country) {
        return CountryGql.fromEntity(countryRepository.save(country.toEntity()));
    }

    @Override
    @Transactional
    public @Nonnull CountryGql updateCountryName(@Nonnull String countryCode, @Nonnull String newCountryName) {
        CountryEntity country = countryRepository.findByCountryCode(countryCode);
        if (country == null) {
            throw new IllegalArgumentException(String.format("Country with code %s not found", countryCode));
        }
        country.setCountryName(newCountryName);

        return CountryGql.fromEntity(countryRepository.save(country));
    }
}