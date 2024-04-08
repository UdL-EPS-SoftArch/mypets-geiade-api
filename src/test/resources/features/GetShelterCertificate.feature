Feature: Get Shelter Certificate
  In order to retrieve shelter certificate information
  As a user with appropriate permissions
  I want to be able to get shelter certificate details

  Background:
    Given There is a registered user with username "username" and password "password" and email "user@email.com"

  Scenario: Get existing shelter certificate details
    Given I can login with username "username" and password "password"
    And There is a registered shelter certificate with id "1" for shelter with name "Shelter Certificate 1"
    When I retrieve the shelter certificate with id "1"
    Then The response code is 200

  Scenario: Get non-existent shelter certificate details
    Given I can login with username "username" and password "password"
    When I retrieve the shelter certificate with id "999"
    Then The response code is 404

  Scenario: Get shelter certificate details when I am not logged in
    Given I'm not logged in
    When I retrieve the shelter certificate with id "1"
    Then The response code is 404