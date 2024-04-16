package cat.udl.eps.softarch.demo.steps;

import cat.udl.eps.softarch.demo.domain.Adoptions;
import cat.udl.eps.softarch.demo.repository.AdoptionsRepository;
import cat.udl.eps.softarch.demo.repository.PetRepository;
import cat.udl.eps.softarch.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.http.MediaType;

import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

public class StepUpdateAdoption {

    @Autowired
    private StepDefs stepDefs;

    @Autowired
    private PetRepository petRepository;

    @Autowired
    private AdoptionsRepository adoptionsRepository;

    @Autowired
    private UserRepository userRepository;

    @When("^Update the adoption with id (\\d+) User with username \"([^\"]*)\"$")
    public void updateAdoptionUsername(int adoption_id, String final_username) {
        Long id_adoption = (long) adoption_id;
        Optional<Adoptions> opt = adoptionsRepository.findById(id_adoption);

        opt.ifPresent(adoptions -> {

            userRepository.findById(final_username).ifPresent(user -> {
                adoptions.setUser(user);
                adoptions.setDateOfAdoption(LocalDateTime.now());
            });
            try {
                stepDefs.result = stepDefs.mockMvc.perform(
                                patch("/adoptions/{id}", adoptions.getId())
                                        .contentType(MediaType.APPLICATION_JSON)
                                        .content(stepDefs.mapper.writeValueAsString(adoptions))
                                        .characterEncoding(StandardCharsets.UTF_8)
                                        .accept(MediaType.APPLICATION_JSON)
                                        .with(AuthenticationStepDefs.authenticate()))
                        .andDo(print());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
    }


    @Then("^The adoption with id (\\d+) should have been updated to username \"([^\"]*)\"")
    public void theAdoptionUserForThePetWithIdShouldBeUpdatedToUsername(int id_int, String username) {
        Long id = (long) id_int;
        Optional<Adoptions> opt = adoptionsRepository.findById(id);

        opt.ifPresent(adoptions -> {
            userRepository.findById(username).ifPresent(user -> assertEquals(user, adoptions.getUser()));
        });
    }


    @And("^The adoption with id (\\d+) dateofAdoption is updated")
    public void theAdoptionDateofAdoptionIsUpdated(int adoption_id) {
        Long id = (long) adoption_id;
        Optional<Adoptions> opt = adoptionsRepository.findById(id);

        opt.ifPresent(adoptions -> {
            assertEquals(adoptions.getDateOfAdoption().getDayOfMonth(), LocalDateTime.now().getDayOfMonth());
            assertEquals(adoptions.getDateOfAdoption().getMonth(), LocalDateTime.now().getMonth());
            assertEquals(adoptions.getDateOfAdoption().getYear(), LocalDateTime.now().getYear());
        });
    }

    @Then("^The system should display an error message indicating the username \"([^\"]*)\" does not exist")
    public void theSystemShouldDisplayAnErrorMessageIndicatingTheUsernameDoesNotExist(String username) {
        assertThat(userRepository.findById(username)).isEmpty();
    }
}
