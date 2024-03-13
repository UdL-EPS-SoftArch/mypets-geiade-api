Feature: Manage Roles
  In order to assign appropriate permissions
  As an admin
  I want to manage roles in the system

  Scenario: Create new role
    Given There is no role named "role"
    When I create a new role named "role"
    Then The response code is 201
    And The role "user" has been created

  Scenario: Create existing role
    Given There is a role named "role"
    When I create a new role named "role"
    Then The response code is 409
    And The role "user" already exists

  Scenario: Get all roles
    Given There are roles named "user", "shelter volunteer", and "admin" in the system
    When I request all roles
    Then The response code is 200
    And The response contains all roles

  Scenario: Delete role
    Given There is a role named "role" in the system
    When I delete the role named "role"
    Then The response code is 204
    And The role "user" has been deleted

  Scenario: Delete non-existing role
    Given There is no role named "non-existing" in the system
    When I delete the role named "non-existing"
    Then The response code is 404
    And The role "non-existing" does not exist

  Scenario: Update role name
    Given There is a role named "role" in the system
    When I update the role named "role" to "member"
    Then The response code is 200
    And The role "role" has been updated to "member"

  Scenario: Update non-existing role
    Given There is no role named "non-existing" in the system
    When I update the role named "non-existing"
    Then The response code is 404
    And The role "non-existing" cannot be updated