package cat.udl.eps.softarch.demo.steps;

import cat.udl.eps.softarch.demo.domain.Shelter;
import cat.udl.eps.softarch.demo.repository.ShelterRepository;
import io.cucumber.java.ParameterType;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

import java.nio.charset.StandardCharsets;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

public class RegisterShelterStepDefs {

    @Autowired
    private StepDefs stepDefs;

    @Autowired
    private ShelterRepository shelterRepository;
    public static String newResourceUri;

    @ParameterType(value = "true|True|TRUE|false|False|FALSE")
    public Boolean booleanValue(String value) {
        return Boolean.valueOf(value);
    }
    @When("I register a new shelter with name \"([^\"]*)\", email \"([^\"]*)\", mobile \"([^\"]*)\" and isActive (True|False)$")
    public void iRegisterANewShelterWithNameEmailMobileAndIsActive(String name, String email, String mobile, boolean isActive) throws Exception {
        Shelter shelter = new Shelter();
        shelter.setName(name);
        shelter.setEmail(email);
        shelter.setMobile(mobile);
        shelter.setActive(true);


        stepDefs.result = stepDefs.mockMvc.perform(post("/shelters")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding(StandardCharsets.UTF_8)
                        .accept(MediaType.APPLICATION_JSON)
                        .with(AuthenticationStepDefs.authenticate()))
                .andDo(print());

        newResourceUri = stepDefs.result.andReturn().getResponse().getHeader("Location");

    }

    @Then("^It has been created a shelter with name \"([^\"]*)\", email \"([^\"]*)\", mobile \"([^\"]*)\", the isActive is not returned$")
    public void itHasBeenCreatedAShelterWithNameEmailMobileAndTheIsActiveIsNotReturned(String name, String email, String mobile) {
        Shelter createdShelter = shelterRepository.findByName(name);
        assertThat(createdShelter).isNotNull();
        assertThat(createdShelter.getEmail()).isEqualTo(email);
        assertThat(createdShelter.getMobile()).isEqualTo(mobile);
    }

    @Then("^It has not been created a shelter with name \"([^\"]*)\"$")
    public void itHasNotBeenCreatedAShelterWithName(String name) {
        Shelter shelter = shelterRepository.findByName(name);
        assertThat(shelter).isNull();
    }


    @Given("There is a registered shelter with name \"([^\"]*)\", email \"([^\"]*)\", mobile \"([^\"]*)\" and isActive {booleanValue}")
    public void thereIsARegisteredShelterWithNameEmailMobileAndIsActiveTrue(String name, String email, String mobile, boolean isActive) {
        Shelter shelter = shelterRepository.findByName(name);
        assertThat(shelter).isNotNull();
    }
}

