package guru.qa.countryng.data.graphql;

import guru.qa.countryng.data.CountryEntity;

import java.time.LocalDateTime;

public record CountryInputGql(String countryName,
                              String countryCode) {

    public CountryEntity toEntity() {
        CountryEntity entity = new CountryEntity();
        entity.setCountryName(this.countryName);
        entity.setCountryCode(this.countryCode);
        entity.setLastModifyDate(LocalDateTime.now());
        return entity;
    }
}