Feature: Get Shelter
  In order to retrieve shelter information
  As a user with appropriate permissions
  I want to be able to get shelter details

  Background:
    Given There is a registered user with username "username" and password "password" and email "user@email.com"

  Scenario: Get existing shelter details
    Given I can login with username "username" and password "password"
    And There is a registered shelter with name "existing_shelter", email "existing_shelter@gmail.com", mobile "+34 123 45 67 89"
    When I retrieve the shelter with name "existing_shelter"
    Then The response code is 200

  Scenario: Get non-existent shelter details
    Given I can login with username "username" and password "password"
    When I retrieve the shelter with name "non_existent_shelter"
    Then The response code is 404

  Scenario: Get shelter details when I am not logged in
    Given I'm not logged in
    When I retrieve the shelter with name "existing_shelter"
    Then The response code is 404