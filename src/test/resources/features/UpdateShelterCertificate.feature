Feature: Update Shelter Certificate
  In order to maintain shelter certificate information
  As a user with appropriate permissions
  I want to be able to update a shelter certificate

  Background:
    Given There is a registered user with username "username" and password "password" and email "user@email.com"

  Scenario: Update existing shelter certificate
    Given I can login with username "username" and password "password"
    And There is a registered shelter certificate with id "1" for shelter with name "existing_shelter"
    When I update the shelter certificate with id "1" and new attributes:
      | expirationDate            |
      | 2023-12-31T00:00:00       |
    Then The response code is 200

  Scenario: Update non-existent shelter certificate
    Given I can login with username "username" and password "password"
    When I update the shelter certificate with id "999" and new attributes:
      | expirationDate            |
      | 2023-12-31T00:00:00       |
    Then The response code is 404

  Scenario: Update shelter certificate when I am not logged in
    Given I'm not logged in
    And There is a registered shelter certificate with id "1" for shelter with name "existing_shelter"
    When I update the shelter certificate with id "1" and new attributes:
      | expirationDate            |
      | 2023-12-31T00:00:00       |
    Then The response code is 401