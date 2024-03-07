package cat.udl.eps.softarch.demo.steps;

import cat.udl.eps.softarch.demo.domain.Location;
import cat.udl.eps.softarch.demo.repository.LocationRepository;
import io.cucumber.java.en.And;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

import java.nio.charset.StandardCharsets;
import java.util.Map;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

public class CreateLocationStepsDefs {

    @Autowired
    private StepDefs stepDefs;

    @Autowired
    private LocationRepository locationRepository;

    public static String newResourceUri;

    @When("I create a new Location with the following details:$")
    public void iCreateANewSeedWithScientificNameAndCommonName(Map<String, String> locationDetails) throws Throwable {
        Location location = new Location();
        location.setAddress(locationDetails.get("address"));
        location.setLatitude(Float.parseFloat(locationDetails.get("latitude")));
        location.setLongitude(Float.parseFloat(locationDetails.get("longitude")));
        location.setProvince(locationDetails.get("province"));
        location.setCity(locationDetails.get("city"));
        location.setPostalCode(Integer.parseInt(locationDetails.get("postalCode")));

        stepDefs.result = stepDefs.mockMvc.perform(
                        post("/locations")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(stepDefs.mapper.writeValueAsString(location))
                                .characterEncoding(StandardCharsets.UTF_8)
                                .accept(MediaType.APPLICATION_JSON)
                                .with(AuthenticationStepDefs.authenticate()))
                .andDo(print());
        newResourceUri = stepDefs.result.andReturn().getResponse().getHeader("Location");
    }

    @And("There is (\\d+) Location created$")
    public void thereIsLocationCreated(int locationCreatedNum) {
        Assert.assertEquals(locationCreatedNum, locationRepository.count());
    }



}
