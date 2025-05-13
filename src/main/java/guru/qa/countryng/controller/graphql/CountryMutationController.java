package guru.qa.countryng.controller.graphql;

import guru.qa.countryng.data.graphql.CountryGql;
import guru.qa.countryng.data.graphql.CountryInputGql;
import guru.qa.countryng.service.impl.GqlCountryServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.stereotype.Controller;

@Controller
public class CountryMutationController {

    private final GqlCountryServiceImpl countryService;

    @Autowired
    public CountryMutationController(GqlCountryServiceImpl countryService) {
        this.countryService = countryService;
    }

    @MutationMapping
    public CountryGql saveCountry(@Argument CountryInputGql input) {
        return countryService.saveCountry(input);
    }

    @MutationMapping
    public CountryGql updateCountry(@Argument CountryInputGql input) {
        return countryService.updateCountryName(input.countryCode(), input.countryName());
    }
}