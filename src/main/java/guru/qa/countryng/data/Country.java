package guru.qa.countryng.data;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;
import java.util.UUID;

public record Country(
        @JsonProperty("id")
        UUID id,
        @JsonProperty("country_name")
        String countryName,
        @JsonProperty("country_code")
        String countryCode,
        @JsonProperty("last_modify_date")
        LocalDateTime lastModifyDate) {

    public static Country fromEntity(CountryEntity ce) {
        return new Country(
                ce.getId(),
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