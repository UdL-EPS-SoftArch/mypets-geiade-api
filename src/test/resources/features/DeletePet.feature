Feature: Delete Pet
  In order to maintain pet data
  As a user with appropriate permissions
  I want to be able to delete a pet

  Background:
    Given There is a registered user with username "username" and password "password" and email "user@email.com"
    And There is a registered shelter with name "shelter1", email "shelter1@gmail.com", mobile "666 55 44 33"
    And There is a registered pet with name "super_cat", date of birth "01-01-2024", isAdopted True, colour "black", sex "male", chip "1234", race "european", isDangerous False, at the shelter named "shelter1"

  Scenario: Delete existing pet
    Given I can login with username "username" and password "password"
    # And I am a user with role "petOwner"  Temporarily commented because we need to do first the Role tests
    When I delete the pet with chip "1234"
    Then The response code is 200
    And The pet with chip "1234" has been deleted

  Scenario: Delete pet when I am not logged in
    Given I'm not logged in
    When I delete the pet with chip "1234"
    Then The response code is 401
    And The pet with chip "1234" has not been deleted

  Scenario: Delete non-existent pet
    Given I can login with username "username" and password "password"
    # And I am a user with role "petOwner"  Temporarily commented because we need to do first the Role tests
    When I delete the pet with chip "9999"
    Then The response code is 404


