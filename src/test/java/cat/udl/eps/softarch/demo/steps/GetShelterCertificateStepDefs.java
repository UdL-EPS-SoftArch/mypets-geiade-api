package cat.udl.eps.softarch.demo.steps;

import cat.udl.eps.softarch.demo.domain.ShelterCertificate;
import cat.udl.eps.softarch.demo.repository.ShelterCertificateRepository;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

import java.nio.charset.StandardCharsets;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

public class GetShelterCertificateStepDefs {
    @Autowired
    private StepDefs stepDefs;

    @Autowired
    private ShelterCertificateRepository shelterCertificateRepository;

    @When("^I retrieve the shelter certificate with id \"([^\"]*)\"$")
    public void iRetrieveShelterCertificateWithId(String id) throws Exception {
        ShelterCertificate shelterCertificate = shelterCertificateRepository.findById(Long.parseLong(id)).orElse(null);

        stepDefs.result = stepDefs.mockMvc.perform(
                        get("/shelterCertificates/{id}", (shelterCertificate != null) ? shelterCertificate.getId() : "999")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(stepDefs.mapper.writeValueAsString(shelterCertificate))
                                .characterEncoding(StandardCharsets.UTF_8)
                                .accept(MediaType.APPLICATION_JSON)
                                .with(AuthenticationStepDefs.authenticate()))
                .andDo(print());
    }
}