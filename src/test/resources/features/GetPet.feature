Feature: Get Pet
  In order to retrieve pet information
  As a user with appropriate permissions
  I want to be able to get pet details

  Background:
    Given There is a registered user with username "username" and password "password" and email "user@email.com"
    And There is a registered shelter with name "shelter1", email "shelter1@gmail.com", mobile "666 55 44 33"

  Scenario: Get existing pet details
    Given I can login with username "username" and password "password"
    And There is a registered pet with name "super_cat", date of birth "01-01-2024", isAdopted True, colour "black", sex "male", chip "1234", race "european", isDangerous False, at the shelter named "shelter1"
    When I retrieve the pet with chip "1234"
    Then The response code is 200

  Scenario: Get non-existent pet details
    Given I can login with username "username" and password "password"
    When I retrieve the pet with chip "1234"
    Then The response code is 404

  Scenario: Get pet details when I am not logged in
    Given I'm not logged in
    When I retrieve the pet with chip "1234"
    Then The response code is 404