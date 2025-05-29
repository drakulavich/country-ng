package guru.qa.countryng.data;

import guru.qa.xml.countryng.SoapCountry;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@Builder
@Entity
@Table(name = "countryng")
@AllArgsConstructor
@NoArgsConstructor
public class CountryEntity {

    @Id
    @Column(name = "id", nullable = false, columnDefinition = "UUID default gen_random_uuid()")
    @GeneratedValue()
    private UUID id;

    @Column(name = "country_name")
    private String countryName;

    @Column(name = "country_code", unique = true)
    private String countryCode;
    
    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "last_modify_date")
    private LocalDateTime lastModifyDate;

    public SoapCountry toSoapCountry() {
        SoapCountry soapCountry = new SoapCountry();
        soapCountry.setId(this.id.toString());
        soapCountry.setCountryName(this.countryName);
        soapCountry.setCountryCode(this.countryCode);
        return soapCountry;
    }
}