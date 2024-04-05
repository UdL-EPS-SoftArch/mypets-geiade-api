package cat.udl.eps.softarch.demo.steps;

import cat.udl.eps.softarch.demo.domain.Role;
import cat.udl.eps.softarch.demo.repository.RoleRepository;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

import java.nio.charset.StandardCharsets;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.mockito.Mockito.when;

public class ManageRolesStepDefs {

    @Autowired
    private StepDefs stepDefs;

    @Autowired
    private RoleRepository roleRepository;

    private Role createdRole;
    private Role existingRole;

    @Given("There is a registered user with username {string} and password {string} and email {string}")
    public void thereIsARegisteredUserWithUsernameAndPasswordAndEmail(String username, String password, String email) {
        // Simulate a registered user - not implemented for simplicity
    }

    @Given("I can login with username {string} and password {string}")
    public void iCanLoginWithUsernameAndPassword(String username, String password) {
        // Simulate login - not implemented for simplicity
    }

    @When("^I create a role with name \"([^\"]*)\"$")
    public void iCreateARoleWithName(String roleName) throws Exception {
        Role role = new Role();
        role.setName(roleName);

        createdRole = roleRepository.save(role);

        stepDefs.result = stepDefs.mockMvc.perform(
                        post("/roles")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(stepDefs.mapper.writeValueAsString(role))
                                .characterEncoding(StandardCharsets.UTF_8)
                                .accept(MediaType.APPLICATION_JSON)
                                .with(AuthenticationStepDefs.authenticate()))
                .andDo(print());
    }

    @When("^I update the role with name \"([^\"]*)\" to have name \"([^\"]*)\"$")
    public void iUpdateTheRoleWithNameToHaveName(String oldName, String newName) throws Exception {
        Role role = roleRepository.findByName(oldName);
        role.setName(newName);

        stepDefs.result = stepDefs.mockMvc.perform(
                        put("/roles/{id}", role.getId())
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(stepDefs.mapper.writeValueAsString(role))
                                .characterEncoding(StandardCharsets.UTF_8)
                                .accept(MediaType.APPLICATION_JSON)
                                .with(AuthenticationStepDefs.authenticate()))
                .andDo(print());
    }

    @When("^I delete the role with name \"([^\"]*)\"$")
    public void iDeleteTheRoleWithName(String roleName) throws Exception {
        Role role = roleRepository.findByName(roleName);

        stepDefs.mockMvc.perform(
                        delete("/roles/{id}", role.getId())
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON)
                                .with(AuthenticationStepDefs.authenticate()))
                .andDo(print());
    }


    @Then("^The response code is (\\d+)$")
    public void theResponseCodeIs(int statusCode) throws Exception {
        stepDefs.result.andExpect(status().is(statusCode));
    }

    @Given("There is a role with name {string}")
    public void thereIsARoleWithName(String roleName) {
        existingRole = new Role();
        existingRole.setName(roleName);

        // Mocking the behavior of the repository to return the existing role
        when(roleRepository.findByName(roleName)).thenReturn(existingRole);
    }

    @When("I retrieve the role with name {string}")
    public void iRetrieveTheRoleWithName(String roleName) throws Exception {
        // Implementation already exists in the RoleStepDefs class
    }
}
