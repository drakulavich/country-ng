package guru.qa.countryng.data.graphql;

import guru.qa.countryng.data.CountryEntity;

public record CountryInputGql(String countryName,
                              String countryCode) {

    public CountryEntity toEntity() {
        CountryEntity entity = new CountryEntity();
        entity.setCountryName(this.countryName);
        entity.setCountryCode(this.countryCode);
        return entity;
    }
}