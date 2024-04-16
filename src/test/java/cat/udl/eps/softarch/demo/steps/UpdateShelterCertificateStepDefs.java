package cat.udl.eps.softarch.demo.steps;

import cat.udl.eps.softarch.demo.domain.ShelterCertificate;
import cat.udl.eps.softarch.demo.repository.ShelterCertificateRepository;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

public class UpdateShelterCertificateStepDefs {
    @Autowired
    private StepDefs stepDefs;

    @Autowired
    private ShelterCertificateRepository shelterCertificateRepository;

    @When("^I update the shelter certificate with id \"([^\"]*)\" and new attributes:$")
    public void iUpdateShelterCertificateWithId(Long id, DataTable table) throws Exception {
        List<Map<String, String>> attributesList = table.asMaps(String.class, String.class);

        // Assuming only one row of attributes is provided, so taking the first map from the list
        Map<String, String> attributes = attributesList.get(0);

        // Fetch attributes from the map
        String expirationDate = attributes.get("expirationDate");

        ShelterCertificate shelterCertificate = shelterCertificateRepository.findById(id).orElse(null);
        if (shelterCertificate != null) {
            if (expirationDate != null) shelterCertificate.setExpirationDate(LocalDateTime.parse(expirationDate));
        }

        stepDefs.result = stepDefs.mockMvc.perform(
                        patch("/shelterCertificates/{id}", (shelterCertificate != null) ? shelterCertificate.getId() : "999")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(stepDefs.mapper.writeValueAsString(shelterCertificate))
                                .characterEncoding(StandardCharsets.UTF_8)
                                .accept(MediaType.APPLICATION_JSON)
                                .with(AuthenticationStepDefs.authenticate()))
                .andDo(print());

    }
}