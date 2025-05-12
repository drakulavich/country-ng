package guru.qa.countryng.data;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;

public record Country(@JsonProperty("country_name")
                      String countryName,
                      @JsonProperty("country_code")
                      String countryCode,
                      @JsonProperty("last_modify_date")
                      LocalDateTime lastModifyDate) {

    public static Country fromEntity(CountryEntity ce) {
        return new Country(
                ce.getCountryName(),
                ce.getCountryCode(),
                ce.getLastModifyDate());
    }

    public CountryEntity toEntity() {
        CountryEntity entity = new CountryEntity();
        entity.setCountryName(this.countryName);
        entity.setCountryCode(this.countryCode);
        entity.setLastModifyDate(this.lastModifyDate);
        return entity;
    }
}