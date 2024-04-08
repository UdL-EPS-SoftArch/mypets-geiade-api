package cat.udl.eps.softarch.demo.steps;


import cat.udl.eps.softarch.demo.domain.Location;
import cat.udl.eps.softarch.demo.repository.LocationRepository;
import io.cucumber.java.en.And;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;


public class GetLocationStepsDefs {

    @Autowired
    private StepDefs stepDefs;

    @Autowired
    private LocationRepository locationRepository;

    public static String newResourceUri;
    @When("I retrieve the location with the details:$")
    public void iRetrieveTheLocationWithTheDetails(Map<String, String> locationDetails) throws Throwable{
        Location existingLocation = locationRepository.findLocationByAddressAndProvinceAndCityAndPostalCode(
                locationDetails.get("address"),
                locationDetails.get("province"),
                locationDetails.get("city"),
                Integer.parseInt(locationDetails.get("postalCode"))
        );

        stepDefs.result = stepDefs.mockMvc.perform(
                        get("/locations/{id}", (existingLocation != null) ? existingLocation.getId() : "999")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(stepDefs.mapper.writeValueAsString(existingLocation))
                                .characterEncoding(StandardCharsets.UTF_8)
                                .accept(MediaType.APPLICATION_JSON)
                                .with(AuthenticationStepDefs.authenticate()))
                .andDo(print());
    }

}
