package cat.udl.eps.softarch.demo.steps;

import cat.udl.eps.softarch.demo.domain.Role;
import cat.udl.eps.softarch.demo.repository.RoleRepository;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

import java.nio.charset.StandardCharsets;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class ManageRolesStepDefs {

    @Autowired
    private StepDefs stepDefs;

    @Autowired
    private RoleRepository roleRepository;

    private Role createdRole;

    @When("^I create a role with name \"([^\"]*)\"$")
    public void iCreateARoleWithName(String roleName) throws Exception {
        Role role = new Role();
        role.setName(roleName);

        stepDefs.result = stepDefs.mockMvc.perform(
                        post("/roles")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(stepDefs.mapper.writeValueAsString(role))
                                .characterEncoding(StandardCharsets.UTF_8)
                                .accept(MediaType.APPLICATION_JSON)
                                .with(AuthenticationStepDefs.authenticate()))
                .andDo(print());
    }

    @Given("There is a role with name {string}")
    public void thereIsARoleWithName(String roleName) {
        Role role = new Role();
        role.setName(roleName);

        roleRepository.save(role);
    }

    @When("I retrieve the role with name {string}")
    public void iRetrieveTheRoleWithName(String roleName) throws Exception {
        Role role = roleRepository.findByName(roleName);

        stepDefs.result = stepDefs.mockMvc.perform(
                        get("/roles/{id}", (role!=null ? role.getId() : 0))
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON)
                                .with(AuthenticationStepDefs.authenticate()))
                .andDo(print());
    }

    @When("I update the role with name {string} to have name {string}")
    public void iUpdateTheRoleWithNameToHaveName(String oldName, String newName) throws Exception {
        Role oldRole = roleRepository.findByName(oldName);
        Role newRole = new Role();
        newRole.setName(newName);

        stepDefs.result = stepDefs.mockMvc.perform(
                        put("/roles/{id}", (oldRole!=null ? oldRole.getId() : 0))
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(stepDefs.mapper.writeValueAsString(newRole))
                                .characterEncoding(StandardCharsets.UTF_8)
                                .accept(MediaType.APPLICATION_JSON)
                                .with(AuthenticationStepDefs.authenticate()))
                .andDo(print());
    }

    @When("I delete the role with name {string}")
    public void iDeleteTheRoleWithName(String roleName) throws Exception {
        Role role = roleRepository.findByName(roleName);

        stepDefs.result = stepDefs.mockMvc.perform(
                        delete("/roles/{id}", (role!=null ? role.getId() : 0))
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON)
                                .with(AuthenticationStepDefs.authenticate()))
                .andDo(print());
    }

    @And("There are {int} roles")
    public void thereAreRoles(int count) {
        assert(roleRepository.count() == count);
    }
}
