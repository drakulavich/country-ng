package guru.qa.countryng.controller.graphql;

import guru.qa.countryng.data.graphql.CountryGql;
import guru.qa.countryng.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class CountryQueryController {

    private final CountryService countryService;

    @Autowired
    public CountryQueryController(CountryService countryService) {
        this.countryService = countryService;
    }

    @QueryMapping
    public List<CountryGql> countries() {
        return countryService.getAllCountriesGql();
    }
}