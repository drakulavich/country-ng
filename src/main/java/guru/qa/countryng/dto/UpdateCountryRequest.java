package guru.qa.countryng.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UpdateCountryRequest {
    @JsonProperty("country_name")
    private String countryName;

    public String getCountryName() {
        return countryName;
    }
}
