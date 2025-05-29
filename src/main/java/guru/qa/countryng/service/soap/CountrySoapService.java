package guru.qa.countryng.service.soap;

import guru.qa.countryng.config.AppConfig;
import guru.qa.countryng.data.CountryEntity;
import guru.qa.countryng.data.CountryRepository;
import guru.qa.xml.countryng.AddCountryRequest;
import guru.qa.xml.countryng.AddCountryResponse;
import guru.qa.xml.countryng.AllCountriesResponse;
import guru.qa.xml.countryng.GetCountryResponse;
import guru.qa.xml.countryng.IdCountryRequest;
import guru.qa.xml.countryng.UpdateCountryRequest;
import guru.qa.xml.countryng.UpdateCountryResponse;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

@Endpoint
public class CountrySoapService {

    private final CountryRepository countryRepository;

    public CountrySoapService(CountryRepository countryRepository)  {
        this.countryRepository = countryRepository;
    }

    @PayloadRoot(namespace = AppConfig.SOAP_NAMESPACE, localPart = "idCountryRequest")
    @ResponsePayload
    public GetCountryResponse getCountry(@RequestPayload IdCountryRequest request) {
        CountryEntity country = countryRepository.findById(UUID.fromString(request.getId()))
                .orElseThrow(() -> new NoSuchElementException("No country with id:" + request.getId() + " found"));

        GetCountryResponse getResponse = new GetCountryResponse();
        getResponse.setCountry(country.toCountryResponse());
        return getResponse;
    }

    @PayloadRoot(namespace = AppConfig.SOAP_NAMESPACE, localPart = "allCountriesRequest")
    @ResponsePayload
    public AllCountriesResponse getAllCountries() {
        List<CountryEntity> allCountries = countryRepository.findAll();
        AllCountriesResponse xmlCountries = new AllCountriesResponse();
        xmlCountries.getCountry().addAll(
                allCountries.stream().map(CountryEntity::toCountryResponse).toList());

        return xmlCountries;
    }

    @PayloadRoot(namespace = AppConfig.SOAP_NAMESPACE, localPart = "addCountryRequest")
    @ResponsePayload
    public AddCountryResponse addCountry(@RequestPayload AddCountryRequest request) {
        CountryEntity countryEntity = new CountryEntity(
                null,
                request.getCountry().getCountryName(),
                request.getCountry().getCountryCode(),
                null);

        CountryEntity createdCountry = countryRepository.save(countryEntity);

        AddCountryResponse addResponse = new AddCountryResponse();
        addResponse.setCountry(createdCountry.toCountryResponse());
        return addResponse;
    }

    @PayloadRoot(namespace = AppConfig.SOAP_NAMESPACE, localPart = "updateCountryRequest")
    @ResponsePayload
    public UpdateCountryResponse updateCountry(@RequestPayload UpdateCountryRequest request) {
        CountryEntity countryEntity = countryRepository.findById(UUID.fromString(request.getId()))
                .orElseThrow(() -> new NoSuchElementException("No country with id:" + request.getId() + " found"));

        CountryEntity updatedCountryEntity = new CountryEntity(
                countryEntity.getId(),
                request.getCountry().getCountryName(),
                request.getCountry().getCountryCode(),
                null);

        CountryEntity updatedCountry = countryRepository.save(updatedCountryEntity);

        UpdateCountryResponse response = new UpdateCountryResponse();
        response.setCountry(updatedCountry.toCountryResponse());
        return response;
    }
}