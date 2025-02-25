package guru.qa.countryng.data;

import com.fasterxml.jackson.annotation.JsonProperty;

public record Country(@JsonProperty("country_name")
                      String countryName,
                      @JsonProperty("country_code")
                      String countryCode) {

    public static Country fromEntity(CountryEntity ce) {
        return new Country(
                ce.getCountryName(),
                ce.getCountryCode());
    }
}