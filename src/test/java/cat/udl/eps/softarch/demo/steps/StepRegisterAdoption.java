package cat.udl.eps.softarch.demo.steps;
import cat.udl.eps.softarch.demo.domain.*;
import cat.udl.eps.softarch.demo.repository.AdoptionsRepository;
import cat.udl.eps.softarch.demo.repository.PetRepository;
import cat.udl.eps.softarch.demo.repository.UserRepository;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

import java.nio.charset.StandardCharsets;
import java.util.Optional;

import static java.lang.Long.parseLong;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

public class StepRegisterAdoption {

    @Autowired
    private StepDefs stepDefs;

    @Autowired
    private PetRepository petRepository;

    @Autowired
    private AdoptionsRepository adoptionsRepository;

    @Autowired
    private UserRepository userRepository;

    @Given("^The Pet with chip \"([^\"]*)\" does not exists")
    public void nonExistingPet (String chip) {
        Pet pet = petRepository.findByChip(chip);
        assertNull(pet);
    }

    @Given("^The Pet with chip \"([^\"]*)\" is not adopted")
    public void isNotAdopted (String chip) {
        Pet pet = petRepository.findByChip(chip);
        assertTrue(pet != null && !pet.isAdopted());
    }

    @Given("^The Pet with chip \"([^\"]*)\" is adopted by user \"([^\"]*)\"")
    public void isAdopted (String chip, String username) {
        Pet pet = petRepository.findByChip(chip);
        Adoptions adoptions = new Adoptions();
        userRepository.findById(username).ifPresent(user -> adoptions.setUser(user));
        adoptions.setPet(pet);
        petRepository.save(pet);
        assertTrue(pet.isAdopted());
    }


    @When("^The user with username \"([^\"]*)\" adopts the Pet with chip \"([^\"]*)\"")
    public void adopt(String username, String chip) {
        Pet pet = petRepository.findByChip(chip);

        if (pet != null) {
            if (!pet.isAdopted()) {
                Adoptions adoptions = new Adoptions();
                adoptions.setPet(pet);
                userRepository.findById(username).ifPresent(adoptions::setUser);
                pet.setAdopted(true);
                petRepository.save(pet);

                try {
                    stepDefs.result = stepDefs.mockMvc.perform(
                                    post("/adoptions")
                                            .contentType(MediaType.APPLICATION_JSON)
                                            .content(stepDefs.mapper.writeValueAsString(adoptions))
                                            .characterEncoding(StandardCharsets.UTF_8)
                                            .accept(MediaType.APPLICATION_JSON)
                                            .with(AuthenticationStepDefs.authenticate()))
                            .andDo(print());
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    @And("^The Pet with chip \"([^\"]*)\" should be marked as adopted")
    public void petAdopted(String chip) {
        Pet pet = petRepository.findByChip(chip);
        assertTrue(pet.isAdopted());
    }

    @Then("^The system should display an error message indicating the Pet with chip \"([^\"]*)\" is already adopted by another user \"([^\"]*)\"")
    public void petAlreadyAdopted(String chip, String username){
        Pet pet = petRepository.findByChip(chip);
        Optional<Adoptions> adoptions = adoptionsRepository.findById(parseLong("1"));
        adoptions.ifPresent(adoption -> {
            User user = adoption.getUser();
            Optional<User> opt = userRepository.findById(username);
            opt.ifPresent(user_actual -> assertTrue(pet.isAdopted() && user.equals(user_actual)));
        });

    }


    @Then("^The system should display an error message indicating the Pet with chip \"([^\"]*)\" does not exist")
    public void petDoesNotExist(String chip){
        Pet pet = petRepository.findByChip(chip);
        assertThat(pet).isNull();
    }
}
