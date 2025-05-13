package guru.qa.countryng.service;

import jakarta.annotation.Nonnull;
import jakarta.transaction.Transactional;

public interface ICountryService<T, I> {
    /**
     * Get all countries
     * @return List of all countries
     */
    Iterable<T> getAllCountries();

    /**
     * Save a new country
     * @param country The country to save
     * @return The saved country
     */
    @Transactional
    @Nonnull T saveCountry(@Nonnull I country);

    /**
     * Update a country name
     * @param countryCode The code of the country to update
     * @param newCountryName The new name for the country
     * @return The updated country
     */
    @Transactional
    @Nonnull T updateCountryName(@Nonnull String countryCode, @Nonnull String newCountryName);
}