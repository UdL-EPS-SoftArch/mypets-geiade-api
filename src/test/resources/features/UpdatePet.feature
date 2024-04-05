Feature: Update Pet
  In order to maintain pet information
  As a user with appropriate permissions
  I want to be able to update a pet

  Background:
    Given There is a registered user with username "username" and password "password" and email "user@email.com"
    And There is a registered shelter with name "shelter1", email "shelter1@gmail.com", mobile "666 55 44 33"

  Scenario: Update existing pet
    Given I can login with username "username" and password "password"
    # And I am a user with role "petOwner"  Temporarily commented because we need to do first the Role tests
    And There is a registered pet with name "super_cat", date of birth "01-01-2024", isAdopted True, colour "black", sex "male", chip "1234", race "european", isDangerous False, at the shelter named "shelter1"
    When I update the pet with chip "1234" and new attributes:
      | name                   | colour             | dateOfBirth       |
      | mega_cat               | white              | 02-02-2024        |
    Then The response code is 200

  Scenario: Update non-existent pet
    Given I can login with username "username" and password "password"
    # And I am a user with role "petOwner"  Temporarily commented because we need to do first the Role tests
    When I update the pet with chip "9999" and new attributes:
      | name                   | colour             | dateOfBirth       |
      | mega_cat               | white              | 02-02-2024        |
    Then The response code is 404

  Scenario: Update pet when I am not logged in
    Given I'm not logged in
    And There is a registered pet with name "super_cat", date of birth "01-01-2024", isAdopted True, colour "black", sex "male", chip "1234", race "european", isDangerous False, at the shelter named "shelter1"
    When I update the pet with chip "1234" and new attributes:
      | name                   | colour             | dateOfBirth       |
      | mega_cat               | white              | 02-02-2024        |
    Then The response code is 401