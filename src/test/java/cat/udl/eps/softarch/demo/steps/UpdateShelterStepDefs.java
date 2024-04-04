package cat.udl.eps.softarch.demo.steps;

import cat.udl.eps.softarch.demo.domain.Shelter;
import cat.udl.eps.softarch.demo.repository.ShelterRepository;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

public class UpdateShelterStepDefs {
    @Autowired
    private StepDefs stepDefs;

    @Autowired
    private ShelterRepository shelterRepository;

    @When("^I update the shelter with name \"([^\"]*)\" and new attributes:$")
    public void iUpdateShelterWithName(String name, DataTable table) throws Exception {
        List<Map<String, String>> attributesList = table.asMaps(String.class, String.class);

        // Assuming only one row of attributes is provided, so taking the first map from the list
        Map<String, String> attributes = attributesList.get(0);

        // Fetch attributes from the map
        String email = attributes.get("email");
        String mobile = attributes.get("mobile");
        String isActive = attributes.get("isActive");

        Shelter shelter = shelterRepository.findByName(name);
        if (shelter != null) {
            if (email != null) shelter.setEmail(email);
            if (mobile != null) shelter.setMobile(mobile);
            if (isActive != null) shelter.setActive(Boolean.parseBoolean(isActive));
            shelter.setUpdatedAt(java.time.LocalDateTime.now());
        }

        stepDefs.result = stepDefs.mockMvc.perform(
                        patch("/shelters/{id}", (shelter != null) ? shelter.getId() : "999")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(stepDefs.mapper.writeValueAsString(shelter))
                                .characterEncoding(StandardCharsets.UTF_8)
                                .accept(MediaType.APPLICATION_JSON)
                                .with(AuthenticationStepDefs.authenticate()))
                .andDo(print());

    }
}
