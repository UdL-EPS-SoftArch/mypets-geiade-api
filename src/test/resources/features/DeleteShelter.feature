Feature: Delete Shelter
  In order to maintain shelter data
  As a user with appropriate permissions
  I want to be able to delete a shelter

  Background:
    Given There is a registered user with username "username" and password "password" and email "user@email.com"
    And There is a registered shelter with name "existing_shelter", email "existing_shelter@gmail.com", mobile "+34 123 45 67 89"

  Scenario: Delete existing shelter
    Given I can login with username "username" and password "password"
    When I delete the shelter with name "existing_shelter"
    Then The response code is 200
    And The shelter with name "existing_shelter" has been deleted

  Scenario: Delete shelter when I am not logged in
    Given I'm not logged in
    When I delete the shelter with name "existing_shelter"
    Then The response code is 401
    And The shelter with name "existing_shelter" has not been deleted

  Scenario: Delete non-existent shelter
    Given I can login with username "username" and password "password"
    When I delete the shelter with name "non_existent_shelter"
    Then The response code is 404


