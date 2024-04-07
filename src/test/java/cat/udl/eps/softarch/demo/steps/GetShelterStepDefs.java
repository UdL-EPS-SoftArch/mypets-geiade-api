package cat.udl.eps.softarch.demo.steps;

import cat.udl.eps.softarch.demo.domain.Shelter;
import cat.udl.eps.softarch.demo.repository.ShelterRepository;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

import java.nio.charset.StandardCharsets;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

public class GetShelterStepDefs {
    @Autowired
    private StepDefs stepDefs;

    @Autowired
    private ShelterRepository shelterRepository;

    @When("^I retrieve the shelter with name \"([^\"]*)\"$")
    public void iRetrieveShelterWithName(String name) throws Exception {
        Shelter shelter = shelterRepository.findByName(name);

        stepDefs.result = stepDefs.mockMvc.perform(
                        get("/shelters/{id}", (shelter != null) ? shelter.getId() : "999")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(stepDefs.mapper.writeValueAsString(shelter))
                                .characterEncoding(StandardCharsets.UTF_8)
                                .accept(MediaType.APPLICATION_JSON)
                                .with(AuthenticationStepDefs.authenticate()))
                .andDo(print());
    }
}
