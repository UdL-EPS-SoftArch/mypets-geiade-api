Feature: Delete Shelter Certificate
  In order to maintain shelter certificate data
  As a user with appropriate permissions
  I want to be able to delete a shelter certificate

  Background:
    Given There is a registered user with username "username" and password "password" and email "user@email.com"
    And There is a registered shelter certificate with id "1" for shelter with name "Shelter Certificate 1"

  Scenario: Delete existing shelter certificate
    Given I can login with username "username" and password "password"
    When I delete the shelter certificate with id "1"
    Then The response code is 200
    And The shelter certificate with id "1" has been deleted

  Scenario: Delete shelter certificate when I am not logged in
    Given I'm not logged in
    When I delete the shelter certificate with id "1"
    Then The response code is 401
    And The shelter certificate with id "1" has not been deleted

  Scenario: Delete non-existent shelter certificate
    Given I can login with username "username" and password "password"
    When I delete the shelter certificate with id "999"
    Then The response code is 404