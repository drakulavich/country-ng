package guru.qa.countryng.service;

import com.google.protobuf.util.Timestamps;
import guru.qa.countryng.data.Country;
import guru.qa.countryng.data.CountryEntity;
import guru.qa.countryng.data.CountryRepository;
import guru.qa.grpc.countryng.AllCountriesRequest;
import guru.qa.grpc.countryng.AllCountriesResponse;
import guru.qa.grpc.countryng.CountCreatedCountries;
import guru.qa.grpc.countryng.CountryGrpc;
import guru.qa.grpc.countryng.CountryRequest;
import guru.qa.grpc.countryng.CountryResponse;
import guru.qa.grpc.countryng.CountryServiceGrpc;
import guru.qa.grpc.countryng.UpdateCountryRequest;
import guru.qa.grpc.countryng.idRequest;
import io.grpc.stub.StreamObserver;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Service
public class GrpcCountryService extends CountryServiceGrpc.CountryServiceImplBase {

    private final CountryRepository countryRepository;

    public GrpcCountryService(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    @Override
    public void getCountry(idRequest request, StreamObserver<CountryResponse> responseObserver) {
        Country country = Country.fromEntity(countryRepository.findById(UUID.fromString(request.getId()))
                .orElseThrow(() -> new NoSuchElementException("No country with id:" + request.getId() + " found")));

        responseObserver.onNext(
                CountryResponse.newBuilder()
                        .setCountry(countryConverterToGrpc(country))
                        .build());
        responseObserver.onCompleted();
    }

    @Override
    public void all(AllCountriesRequest request, StreamObserver<AllCountriesResponse> responseObserver) {
        List<CountryGrpc> allCountriesResponseList = countryRepository.findAll().stream()
                .map(countryEntity -> countryConverterToGrpc(Country.fromEntity(countryEntity)))
                .collect(Collectors.toList());

        responseObserver.onNext(
                AllCountriesResponse.newBuilder()
                        .addAllCountries(allCountriesResponseList)
                        .build());
        responseObserver.onCompleted();
    }

    @Override
    public StreamObserver<CountryRequest> add(StreamObserver<CountCreatedCountries> responseObserver) {
        AtomicInteger totalAdded = new AtomicInteger(0);

        return new StreamObserver<>() {
            @Override
            public void onNext(CountryRequest country) {
                CountryEntity countryEntity = new CountryEntity(
                        null,
                        country.getCountryName(),
                        country.getCountryCode(),
                        LocalDateTime.now());
                countryRepository.save(countryEntity);
                totalAdded.incrementAndGet();
            }

            @Override
            public void onError(Throwable t) {
                responseObserver.onError(t);
            }

            @Override
            public void onCompleted() {
                CountCreatedCountries response = CountCreatedCountries.newBuilder()
                        .setCount(totalAdded.get())
                        .build();

                responseObserver.onNext(response);
                responseObserver.onCompleted();
            }
        };
    }


    @Override
    public void update(UpdateCountryRequest request, StreamObserver<CountryResponse> responseObserver) {
        CountryEntity countryEntity = countryRepository.findById(UUID.fromString(request.getId()))
                .orElseThrow(() -> new NoSuchElementException("No country with id:" + request.getId() + " found"));

        CountryEntity updatedCountryEntity = new CountryEntity(
                countryEntity.getId(),
                request.getCountry().getCountryName(),
                request.getCountry().getCountryCode(),
                LocalDateTime.now());

        responseObserver.onNext(
                CountryResponse.newBuilder()
                        .setCountry(
                                countryConverterToGrpc(Country.fromEntity(countryRepository.save(updatedCountryEntity))))
                        .build());
        responseObserver.onCompleted();
    }

    private CountryGrpc countryConverterToGrpc(Country country) {
        Date date = Date.from(country.lastModifyDate()
                .atZone(ZoneId.systemDefault()) // attach time zone
                .toInstant());

        return CountryGrpc.newBuilder()
                .setId(country.id().toString())
                .setCountryCode(country.countryCode())
                .setCountryName(country.countryName())
                .setLastModifyDate(Timestamps.fromDate(date))
                .build();
    }
}
