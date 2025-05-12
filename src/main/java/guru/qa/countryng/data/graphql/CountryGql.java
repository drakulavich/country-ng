package guru.qa.countryng.data.graphql;

import guru.qa.countryng.data.CountryEntity;

import java.time.ZoneId;
import java.util.Date;
import java.util.UUID;

public record CountryGql(UUID id,
                         String countryName,
                         String countryCode,
                         Date lastModifyDate) {

    public static CountryGql fromEntity(CountryEntity ce) {
        // Convert LocalDateTime to Date
        Date date = Date.from(ce.getLastModifyDate()
                .atZone(ZoneId.systemDefault()) // attach time zone
                .toInstant());

        return new CountryGql(
                ce.getId(),
                ce.getCountryName(),
                ce.getCountryCode(),
                date);
    }
}