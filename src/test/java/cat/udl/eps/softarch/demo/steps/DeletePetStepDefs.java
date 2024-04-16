package cat.udl.eps.softarch.demo.steps;

import cat.udl.eps.softarch.demo.domain.Pet;
import cat.udl.eps.softarch.demo.repository.PetRepository;
import io.cucumber.java.en.And;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

import java.nio.charset.StandardCharsets;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;


public class DeletePetStepDefs {
    @Autowired
    private StepDefs stepDefs;

    @Autowired
    private PetRepository petRepository;

    @When("^I delete the pet with chip \"([^\"]*)\"$")
    public void iDeleteAPetWithChip(String chip) throws Exception {
        Pet pet = petRepository.findByChip(chip);

        stepDefs.result = stepDefs.mockMvc.perform(
                        delete("/pets/{id}", (pet != null) ? pet.getId() : "999")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(stepDefs.mapper.writeValueAsString(pet))
                                .characterEncoding(StandardCharsets.UTF_8)
                                .accept(MediaType.APPLICATION_JSON)
                                .with(AuthenticationStepDefs.authenticate()))
                .andDo(print());
    }

    @And("^The pet with chip \"([^\"]*)\" has been deleted$")
    public void thePetWithChipHasBeenDeleted(String chip) {
        Pet pet = petRepository.findByChip(chip);
        assertThat(pet).isNull();
    }

    @And("^The pet with chip \"([^\"]*)\" has not been deleted$")
    public void thePetWithChipHasNotBeenDeleted(String chip) {
        Pet pet = petRepository.findByChip(chip);
        assertThat(pet).isNotNull();
    }
}
