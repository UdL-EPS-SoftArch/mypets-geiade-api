package cat.udl.eps.softarch.demo.steps;

import cat.udl.eps.softarch.demo.domain.ShelterCertificate;
import cat.udl.eps.softarch.demo.repository.ShelterCertificateRepository;
import io.cucumber.java.en.And;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

import java.nio.charset.StandardCharsets;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;


public class DeleteShelterCertificateStepDefs {
    @Autowired
    private StepDefs stepDefs;

    @Autowired
    private ShelterCertificateRepository shelterCertificateRepository;

    @When("^I delete the shelter certificate with id \"([^\"]*)\"$")
    public void iDeleteAShelterCertificateWithId(Long id) throws Exception {
        ShelterCertificate shelterCertificate = shelterCertificateRepository.findById(id).orElse(null);

        stepDefs.result = stepDefs.mockMvc.perform(
                        delete("/shelterCertificates/{id}", (shelterCertificate != null) ? shelterCertificate.getId() : "999")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(stepDefs.mapper.writeValueAsString(shelterCertificate))
                                .characterEncoding(StandardCharsets.UTF_8)
                                .accept(MediaType.APPLICATION_JSON)
                                .with(AuthenticationStepDefs.authenticate()))
                .andDo(print());
    }

    @And("^The shelter certificate with id \"([^\"]*)\" has been deleted$")
    public void theShelterCertificateWithIdHasBeenDeleted(Long id) {
        boolean exists = shelterCertificateRepository.existsById(id);
        assertThat(exists).isFalse();
    }

    @And("^The shelter certificate with id \"([^\"]*)\" has not been deleted$")
    public void theShelterCertificateWithIdHasNotBeenDeleted(Long id) {
        boolean exists = shelterCertificateRepository.existsById(id);
        assertThat(exists).isTrue();
    }
}