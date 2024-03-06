package cat.udl.eps.softarch.demo.steps;

import cat.udl.eps.softarch.demo.domain.Pet;
import cat.udl.eps.softarch.demo.domain.User;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class StepRegisterAdoption {
    private Pet pet;

    private User user;

    @Given("The user is logged in with username \"username\" and password \"password\"")
    public void userLogged(String username, String password) {

    }

    @And("The Pet with id \"id\" exists and is not adopted")
    public void existingPet (String id, Boolean isAdopted) {

    }

    @When("The user adopts the Pet with id \"id\"")
    public void adopt(User user, Pet pet) {

    }

    @Then("The Pet with id \"id\" should be marked as adopted")
    public void petAdopted(Pet pet) {

    }
}
