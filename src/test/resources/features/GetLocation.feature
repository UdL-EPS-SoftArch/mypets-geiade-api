Feature: Get Location
  In order to retrieve Location information
  As a user with appropriate permissions
  I want to be able to get the Location details of a shelter

  Background:
    Given There is a registered user with username "username" and password "password" and email "user@email.com"


  Scenario: Get an existing Location details
    Given I can login with username "username" and password "password"
    And There is already a Location with the following details:
      | address       | Major Street 3     |
      | province      | Lleida             |
      | city          | Seròs              |
      | postalCode    | 25183              |
    When I retrieve the location with the details:
      | address       | Major Street 3     |
      | province      | Lleida             |
      | city          | Seròs              |
      | postalCode    | 25183              |
    Then The response code is 200

  Scenario: Get a non-existent Location details
    Given I can login with username "username" and password "password"
    When I retrieve the location with the details:
      | address       | Major Street 3     |
      | province      | Lleida             |
      | city          | Seròs              |
      | postalCode    | 25183              |
    Then The response code is 404
