Feature: Create Location
  In order to efficiently manage locations
  I want to be able to create a new Location record in the system

  Background:
    Given There is a registered user with username "username" and password "password" and email "user@domain.com"

  Scenario: Create a new Location with valid address, latitude, longitude, province, city, and postal code
    Given I can login with username "username" and password "password"
    When I create a new Location with the following details:
      | address       | Major Street 3     |
      | latitude      | 40.7128            |
      | longitude     | -74.0060           |
      | province      | Lleida             |
      | city          | Seròs              |
      | postalCode    | 25183              |
    Then The response code is 201
    And There is 1 Location created

  Scenario: Create a Location with an existing address, city, province, and postal code
    Given I can login with username "username" and password "password"
    And There is already a Location with the following details:
      | address       | Major Street 3 |
      | city          | Seròs          |
      | province      | Lleida         |
      | postalCode    | 25183          |
    When I attempt to create a new Location with the following details:
      | address       | Major Street 3     |
      | latitude      | 41.6167            |
      | longitude     | 0.6222             |
      | province      | Lleida             |
      | city          | Seròs              |
      | postalCode    | 25183              |
    Then The response code is 409
    And There is 0 Location created

  Scenario: Attempt to create a Location with address blank and latitude null
    Given I can login with username "username" and password "password"
    When I create a new Location with the following details:
      | address       |                  |
      | latitude      | null             |
      | longitude     | -74.0060         |
      | province      | Lleida           |
      | city          | Seròs            |
      | postalCode    | 25183            |
    Then The response code is 400
    And There is 0 Location created

  Scenario: Attempt to create a Location without being logged in
    Given I'm not logged in
    When I create a new Location with the following details:
      | address       | Major Street 3 |
      | latitude      | 40.7128        |
      | longitude     | -74.0060       |
      | province      | Lleida         |
      | city          | Seròs          |
      | postalCode    | 25183          |
    Then The response code is 401
    And There is 0 Location created