package cat.udl.eps.softarch.demo.steps;

import cat.udl.eps.softarch.demo.domain.Shelter;
import cat.udl.eps.softarch.demo.domain.ShelterCertificate;
import cat.udl.eps.softarch.demo.repository.ShelterCertificateRepository;
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

public class RegisterShelterCertificateStepDefs {

    @Autowired
    private StepDefs stepDefs;

    @Autowired
    private ShelterCertificateRepository shelterCertificateRepository;

    @Autowired
    private ShelterRepository shelterRepository;

    public static String newResourceUri;

    @When("I register a new shelter certificate with id \"([^\"]*)\" for shelter with name \"([^\"]*)\"$")
    public void iRegisterANewShelterCertificateWithIdForShelterWithName(Long id, String shelterName) throws Exception {
        ShelterCertificate shelterCertificate = new ShelterCertificate();

        Shelter shelter = shelterRepository.findByName(shelterName);
        shelterCertificate.setShelter(shelter);

        stepDefs.result = stepDefs.mockMvc.perform(
                        post("/shelterCertificates")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(stepDefs.mapper.writeValueAsString(shelterCertificate))
                                .characterEncoding(StandardCharsets.UTF_8)
                                .accept(MediaType.APPLICATION_JSON)
                                .with(AuthenticationStepDefs.authenticate()))
                .andDo(print());

        newResourceUri = stepDefs.result.andReturn().getResponse().getHeader("Location");

    }

    @Then("^It has been created a shelter certificate with id \"([^\"]*)\" for shelter with name \"([^\"]*)\"$")
    public void itHasBeenCreatedAShelterCertificateWithIdForShelterWithName(Long id, String shelterName) {
        ShelterCertificate createdShelterCertificate = shelterCertificateRepository.findById(id).orElse(null);
        assertThat(createdShelterCertificate).isNotNull();
        assertThat(createdShelterCertificate.getShelter().getName()).isEqualTo(shelterName);
    }

    @Then("^It has not been created a shelter certificate with id \"([^\"]*)\"$")
    public void itHasNotBeenCreatedAShelterCertificateWithId(Long id) {
        boolean exists = shelterCertificateRepository.existsById(id);
        assertThat(exists).isFalse();
    }

    @Given("^There is a registered shelter certificate with id \"([^\"]*)\" for shelter with name \"([^\"]*)\"$")
    public void thereIsARegisteredShelterCertificateWithIdForShelterWithName(Long id, String shelterName) {
        ShelterCertificate shelterCertificate = new ShelterCertificate();

        Shelter shelter = shelterRepository.findByName(shelterName);
        shelterCertificate.setShelter(shelter);

        shelterCertificateRepository.save(shelterCertificate);

        boolean exists = shelterCertificateRepository.existsById(id);
        assertThat(exists).isTrue();
    }
}