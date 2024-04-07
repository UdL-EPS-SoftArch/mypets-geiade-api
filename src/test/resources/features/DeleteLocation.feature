Feature: Delete Location
  In order to delete an existing Location
  As a user with appropriate permissions
  I want to be able to delete the Location details of a shelter

  Background:
    Given There is a registered user with username "username" and password "password" and email "user@email.com"
    And There is already a Location with the following details:
      | address       | Major Street 3     |
      | province      | Lleida             |
      | city          | Seròs              |
      | postalCode    | 25183              |


  Scenario: Delete an existing Location details
    Given I can login with username "username" and password "password"
    When I delete the location with the details:
      | address       | Major Street 3     |
      | province      | Lleida             |
      | city          | Seròs              |
      | postalCode    | 25183              |
    Then The response code is 200
    And There is 0 Location with the details:
      | address       | Major Street 3     |
      | province      | Lleida             |
      | city          | Seròs              |
      | postalCode    | 25183              |

  Scenario: Delete Location when I am not logged in
    Given I'm not logged in
    When I delete the location with the details:
      | address       | Major Street 3     |
      | province      | Lleida             |
      | city          | Seròs              |
      | postalCode    | 25183              |
    Then The response code is 401
    And There is 1 Location with the details:
      | address       | Major Street 3     |
      | province      | Lleida             |
      | city          | Seròs              |
      | postalCode    | 25183              |