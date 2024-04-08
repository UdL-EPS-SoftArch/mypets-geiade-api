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

import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;


public class CreateLocationStepsDefs {

    @Autowired
    private StepDefs stepDefs;

    @Autowired
    private LocationRepository locationRepository;

    public static String newResourceUri;

    @When("There is already a Location with the following details:$")
    public void thereIsAlreadyALocationWithTheFollowingDetails(Map<String, String> locationDetails) throws Throwable{
        Location location = new Location();
        location.setAddress(locationDetails.get("address"));
        location.setProvince(locationDetails.get("province"));
        location.setCity(locationDetails.get("city"));
        location.setPostalCode(Integer.parseInt(locationDetails.get("postalCode")));
        locationRepository.save(location);
    }
    @When("I create a new Location with the following details:$")
    public void iCreateANewLocationWithTheFollowingDetails(Map<String, String> locationDetails) throws Throwable {
        Location existingLocation = locationRepository.findLocationByAddressAndProvinceAndCityAndPostalCode(
                locationDetails.get("address"),
                locationDetails.get("province"),
                locationDetails.get("city"),
                Integer.parseInt(locationDetails.get("postalCode"))
        );

        if (existingLocation == null) {

            Location location = new Location();
            location.setAddress(locationDetails.get("address"));
            location.setProvince(locationDetails.get("province"));
            location.setCity(locationDetails.get("city"));
            location.setPostalCode(Integer.parseInt(locationDetails.get("postalCode")));

            // Verify and assign latitude and longitude if exists
            if (locationDetails.containsKey("longitude") && locationDetails.get("longitude") != null) {
                location.setLongitude(Float.parseFloat(locationDetails.get("longitude")));
            }


            if (locationDetails.containsKey("latitude") && locationDetails.get("latitude") != null) {
                location.setLatitude(Float.parseFloat(locationDetails.get("latitude")));
            }

            stepDefs.result = stepDefs.mockMvc.perform(
                            post("/locations")
                                    .contentType(MediaType.APPLICATION_JSON)
                                    .content(stepDefs.mapper.writeValueAsString(location))
                                    .characterEncoding(StandardCharsets.UTF_8)
                                    .accept(MediaType.APPLICATION_JSON)
                                    .with(AuthenticationStepDefs.authenticate()))
                    .andDo(print());
            newResourceUri = stepDefs.result.andReturn().getResponse().getHeader("Location");

        } else {
            assertThat(existingLocation).isNotNull();
        }
    }



    @And("There is (\\d+) Location created$")
    public void thereIsLocationCreated(int locationCreatedNum) {
        Assert.assertEquals(locationCreatedNum, locationRepository.count());
    }

    @And("There is only (\\d+) Location with the details:$")
    public void thereIsOnlyLocationWithTheDeatils(int locationCreatedNum, Map<String, String> locationDetails) {
        Assert.assertEquals(locationCreatedNum, locationRepository.count());
    }


}
