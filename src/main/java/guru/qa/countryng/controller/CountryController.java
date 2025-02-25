package guru.qa.countryng.controller;

import guru.qa.countryng.data.Country;
import guru.qa.countryng.dto.UpdateCountryRequest;
import guru.qa.countryng.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CountryController {

    private final CountryService countryService;

    @Autowired
    public CountryController(CountryService countryService) {
        this.countryService = countryService;
    }

    @GetMapping("/countries")
    public List<Country> getAll() {
        return countryService.getAllCountries();
    }

    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public Country addCountry(@RequestBody Country country) {
        return countryService.saveCountry(country);
    }

    @PatchMapping("/country/{countryName}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Country updateCountryName(@PathVariable String countryName,
                                     @RequestBody UpdateCountryRequest requestData) {
        return countryService.updateCountryName(countryName, requestData.getCountryName());
    }
}