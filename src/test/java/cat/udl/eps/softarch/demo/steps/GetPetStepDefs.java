package cat.udl.eps.softarch.demo.steps;

import cat.udl.eps.softarch.demo.domain.Pet;
import cat.udl.eps.softarch.demo.repository.PetRepository;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

import java.nio.charset.StandardCharsets;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

public class GetPetStepDefs {
    @Autowired
    private StepDefs stepDefs;

    @Autowired
    private PetRepository petRepository;

    @When("^I retrieve the pet with chip \"([^\"]*)\"$")
    public void iRetrievePetWithChip(String chip) throws Exception {
        Pet pet = petRepository.findByChip(chip);

        stepDefs.result = stepDefs.mockMvc.perform(
                        get("/pets/{id}", (pet != null) ? pet.getId() : "999")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(stepDefs.mapper.writeValueAsString(pet))
                                .characterEncoding(StandardCharsets.UTF_8)
                                .accept(MediaType.APPLICATION_JSON)
                                .with(AuthenticationStepDefs.authenticate()))
                .andDo(print());
    }
}
