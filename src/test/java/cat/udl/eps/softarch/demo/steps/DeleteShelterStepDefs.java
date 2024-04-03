package cat.udl.eps.softarch.demo.steps;

import cat.udl.eps.softarch.demo.domain.Shelter;
import cat.udl.eps.softarch.demo.repository.ShelterRepository;
import io.cucumber.java.en.And;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

import java.nio.charset.StandardCharsets;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.assertj.core.api.Assertions.assertThat;


public class DeleteShelterStepDefs {
    @Autowired
    private StepDefs stepDefs;

    @Autowired
    private ShelterRepository shelterRepository;

    @When("^I delete the shelter with name \"([^\"]*)\"$")
    public void iDeleteAShelterWithName(String name) throws Exception {
        Shelter shelter = shelterRepository.findByName(name);

        stepDefs.result = stepDefs.mockMvc.perform(
                        delete("/shelters/{id}", (shelter != null) ? shelter.getId() : "999")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(stepDefs.mapper.writeValueAsString(shelter))
                                .characterEncoding(StandardCharsets.UTF_8)
                                .accept(MediaType.APPLICATION_JSON)
                                .with(AuthenticationStepDefs.authenticate()))
                .andDo(print());
    }

    @And("^The shelter with name \"([^\"]*)\" has been deleted$")
    public void theShelterWithNameHasBeenDeleted(String name) {
        Shelter shelter = shelterRepository.findByName(name);
        assertThat(shelter).isNull();
    }

    @And("^The shelter with name \"([^\"]*)\" has not been deleted$")
    public void theShelterWithNameHasNotBeenDeleted(String name) {
        Shelter shelter = shelterRepository.findByName(name);
        assertThat(shelter).isNotNull();
    }
}
