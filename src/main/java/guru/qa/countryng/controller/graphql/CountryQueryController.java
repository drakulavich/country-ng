package guru.qa.countryng.controller.graphql;

import guru.qa.countryng.data.graphql.CountryGql;
import guru.qa.countryng.service.impl.GqlCountryServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class CountryQueryController {

    private final GqlCountryServiceImpl countryService;

    @Autowired
    public CountryQueryController(GqlCountryServiceImpl countryService) {
        this.countryService = countryService;
    }

    @QueryMapping
    public List<CountryGql> countries() {
        return countryService.getAllCountries();
    }
}