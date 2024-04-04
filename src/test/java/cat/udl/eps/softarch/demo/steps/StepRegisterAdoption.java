package cat.udl.eps.softarch.demo.steps;
import cat.udl.eps.softarch.demo.domain.Adoptions;
import cat.udl.eps.softarch.demo.domain.Cat;
import cat.udl.eps.softarch.demo.domain.Pet;
import cat.udl.eps.softarch.demo.domain.User;
import cat.udl.eps.softarch.demo.repository.AdoptionsRepository;
import cat.udl.eps.softarch.demo.repository.PetRepository;
import cat.udl.eps.softarch.demo.repository.UserRepository;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

import static java.lang.Long.parseLong;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;
import static org.mockito.internal.matchers.text.ValuePrinter.print;

public class StepRegisterAdoption {

    @Autowired
    private StepDefs stepDefs;

    @Autowired
    private PetRepository petRepository;

    @Autowired
    private AdoptionsRepository adoptionsRepository;

    @Autowired
    private UserRepository userRepository;



    @Given("^The Pet with id (\\d+) does not exists")
    public void nonExistingPet (int id_str) {
        Long id = (long) id_str;
        Pet pet = petRepository.findPetById(id);
        assertNull(pet);
    }

    @Given("^The Pet with id (\\d+) is not adopted")
    public void isNotAdopted (int id_int) {
        Long id = (long) id_int;
        Pet pet = petRepository.findPetById(id);
        assertTrue(pet != null && !pet.isAdopted());
    }

    @Given("^The Pet with id (\\d+) is adopted by user \"([^\"]*)\"")
    public void isAdopted (int id_int, String username) {
        Long id = (long) id_int;
        Pet pet = petRepository.findPetById(id);
        Adoptions adoptions = new Adoptions();
        userRepository.findById(username).ifPresent(user -> adoptions.setUser(user));
        adoptions.setPet(pet);
        pet.setAdopted(true);
        petRepository.save(pet);
        assertTrue(pet.isAdopted());
    }


    @When("^The user with username \"([^\"]*)\" adopts the Pet with id (\\d+)")
    public void adopt(String username, int petId) {
        Long id = (long) petId;
        Pet pet = petRepository.findPetById(id);

        if (pet != null) {
            if (!pet.isAdopted()) {
                Adoptions adoptions = new Adoptions();
                adoptions.setPet(pet);
                userRepository.findById(username).ifPresent(user -> adoptions.setUser(user));
                pet.setAdopted(true);
                petRepository.save(pet);
            }
        }
    }

    @And("^The Pet with id (\\d+) should be marked as adopted")
    public void petAdopted(int id_int) {
        Long id = (long) id_int;
        Pet pet = petRepository.findPetById(id);
        assertTrue(pet.isAdopted());
    }

    @Then("^The system should display an error message indicating the Pet with id (\\d+) is already adopted by another user \"([^\"]*)\"")
    public void petAlreadyAdopted(int id_int, String username){
        Long id = (long) id_int;
        Pet pet = petRepository.findPetById(id);
        Optional<Adoptions> adoptions = adoptionsRepository.findById(id);
        adoptions.ifPresent(adoption -> {
            User user = adoption.getUser();
            Optional<User> opt = userRepository.findById(username);
            opt.ifPresent(user_actual -> assertTrue(pet.isAdopted() && user.equals(user_actual)));
        });

    }


    @Then("^The system should display an error message indicating the Pet with id (\\d+) does not exist")
    public void petDoesNotExist(int id_int){
        Long id = (long) id_int;
        Pet pet = petRepository.findPetById(id);
        assertThat(pet).isNull();
    }

    @Given("^The Pet with id (\\d+) exists")
    public void thePetWithIdExists(int id_str) {
        Long id = (long) id_str;
        Cat cat = new Cat();
        cat.setAdopted(false);
        petRepository.save(cat);
        Pet pet = petRepository.findPetById(id);
        assertNotNull(pet);
    }

}
