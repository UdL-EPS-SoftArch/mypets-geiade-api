package cat.udl.eps.softarch.demo.steps;

import cat.udl.eps.softarch.demo.domain.Cat;
import cat.udl.eps.softarch.demo.domain.Pet;
import cat.udl.eps.softarch.demo.domain.Shelter;
import cat.udl.eps.softarch.demo.repository.PetRepository;
import cat.udl.eps.softarch.demo.repository.ShelterRepository;
import io.cucumber.cucumberexpressions.Transformer;
import io.cucumber.java.ParameterType;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.checkerframework.checker.formatter.qual.Format;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

public class RegisterPetStepDefs {

    @Autowired
    private StepDefs stepDefs;

    @Autowired
    private PetRepository petRepository;

    @Autowired
    private ShelterRepository shelterRepository;
    public static String newResourceUri;

    public static LocalDate dateValue(String value) {
        return LocalDate.parse(value, DateTimeFormatter.ofPattern("dd-MM-yyyy"));
    }

    @ParameterType(name= "boolean", value = "true|True|TRUE|false|False|FALSE")
    public Boolean booleanValue(String value) {
        return Boolean.valueOf(value);
    }

    @When("I register a new pet with name {string}, date of birth {string}, isAdopted {boolean}, colour {string}, sex {string}, chip {string}, race {string}, isDangerous {boolean}, at the shelter named {string}")
    public void iRegisterANewPetWithData(String name, String dateOfBirth, boolean adoptionStatus, String colour, String sex, String chip, String race, boolean isDangerous, String shelterName) throws Exception {
        Pet pet = new Pet();
        pet.setName(name);
        pet.setDateOfBirth(dateValue(dateOfBirth));
        pet.setAdopted(adoptionStatus);
        pet.setColour(colour);
        pet.setSex(sex);
        pet.setChip(chip);
        pet.setRace(race);
        pet.setDangerous(isDangerous);

        Shelter shelter = shelterRepository.findByName(shelterName);
        pet.setShelter(shelter);


        stepDefs.result = stepDefs.mockMvc.perform(
                post("/pets")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(stepDefs.mapper.writeValueAsString(pet))
                        .characterEncoding(StandardCharsets.UTF_8)
                        .accept(MediaType.APPLICATION_JSON)
                        .with(AuthenticationStepDefs.authenticate()))
                .andDo(print());

        newResourceUri = stepDefs.result.andReturn().getResponse().getHeader("Location");

    }

    @Then("It has been created a pet with name {string}, date of birth {string}, isAdopted {boolean}, colour {string}, sex {string}, chip {string}, race {string}, isDangerous {boolean}, at the shelter named {string}")
    public void itHasBeenCreatedAPetWithData(String name, String dateOfBirth, boolean adoptionStatus, String colour, String sex, String chip, String race, boolean isDangerous, String shelterName) {
        Pet createdPet = petRepository.findByChip(chip);
        Shelter shelter = shelterRepository.findByName(shelterName);
        assertThat(createdPet).isNotNull();
        assertThat(createdPet.getName()).isEqualTo(name);
        assertThat(createdPet.getDateOfBirth()).isEqualTo(dateValue(dateOfBirth));
        assertThat(createdPet.isAdopted()).isEqualTo(adoptionStatus);
        assertThat(createdPet.getColour()).isEqualTo(colour);
        assertThat(createdPet.getSex()).isEqualTo(sex);
        assertThat(createdPet.getChip()).isEqualTo(chip);
        assertThat(createdPet.getRace()).isEqualTo(race);
        assertThat(createdPet.isDangerous()).isEqualTo(isDangerous);
        assertThat(createdPet.getShelter()).isEqualTo(shelter);
    }

    @Then("It has not been created a pet with chip {string}")
    public void itHasNotBeenCreatedAPetWithChip(String chip) {
        Pet pet = petRepository.findByChip(chip);
        assertThat(pet).isNull();
    }

    @Then("It has not been created a pet with name {string}")
    public void itHasNotBeenCreatedAPetWithName(String name) {
        Pet pet = petRepository.findByName(name);
        assertThat(pet).isNull();
    }

    @Given("There is a registered pet with name {string}, date of birth {string}, isAdopted {boolean}, colour {string}, sex {string}, chip {string}, race {string}, isDangerous {boolean}, at the shelter named {string}")
    public void thereIsARegisteredPetWithData(String name, String dateOfBirth, boolean adoptionStatus, String colour, String sex, String chip, String race, boolean isDangerous, String shelterName) {
        Pet pet = new Pet();
        pet.setName(name);
        pet.setDateOfBirth(dateValue(dateOfBirth));
        pet.setAdopted(adoptionStatus);
        pet.setColour(colour);
        pet.setSex(sex);
        pet.setChip(chip);
        pet.setRace(race);
        pet.setDangerous(isDangerous);

        Shelter shelter = shelterRepository.findByName(shelterName);
        pet.setShelter(shelter);

        petRepository.save(pet);

        Pet petResult = petRepository.findByName(name);
        assertThat(petResult).isNotNull();
    }
}

