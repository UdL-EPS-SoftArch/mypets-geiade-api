package cat.udl.eps.softarch.demo.steps;

import cat.udl.eps.softarch.demo.domain.Pet;
import cat.udl.eps.softarch.demo.repository.PetRepository;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

public class UpdatePetStepDefs {
    @Autowired
    private StepDefs stepDefs;

    @Autowired
    private PetRepository petRepository;

    @When("^I update the pet with chip \"([^\"]*)\" and new attributes:$")
    public void iUpdatePetWithChip(String chip, DataTable table) throws Exception {
        List<Map<String, String>> attributesList = table.asMaps(String.class, String.class);

        // Assuming only one row of attributes is provided, so taking the first map from the list
        Map<String, String> attributes = attributesList.get(0);

        // Fetch attributes from the map
        String name = attributes.get("name");
        String dateOfBirth = attributes.get("dateOfBirth");
        String isAdopted = attributes.get("isAdopted");
        String colour = attributes.get("colour");
        String size = attributes.get("size");
        String sex = attributes.get("sex");
        String race = attributes.get("race");
        String isDangerous = attributes.get("isDangerous");

        Pet pet = petRepository.findByChip(chip);
        if (pet != null) {
            if (name != null) pet.setName(name);
            if (dateOfBirth != null) pet.setDateOfBirth(LocalDate.parse(dateOfBirth, DateTimeFormatter.ofPattern("dd-MM-yyyy")));
            if (isAdopted != null) pet.setAdopted(Boolean.parseBoolean(isAdopted));
            if (colour != null) pet.setColour(colour);
            if (size != null) pet.setColour(size);
            if (sex != null) pet.setSex(sex);
            if (race != null) pet.setRace(race);
            if (isDangerous != null) pet.setDangerous(Boolean.parseBoolean(isDangerous));
        }

        stepDefs.result = stepDefs.mockMvc.perform(
                        patch("/pets/{id}", (pet != null) ? pet.getId() : "999")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(stepDefs.mapper.writeValueAsString(pet))
                                .characterEncoding(StandardCharsets.UTF_8)
                                .accept(MediaType.APPLICATION_JSON)
                                .with(AuthenticationStepDefs.authenticate()))
                .andDo(print());

    }
}
