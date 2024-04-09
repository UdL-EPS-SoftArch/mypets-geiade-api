Feature: Manage Roles
  In order to manage roles in the system
  As a user with appropriate permissions
  I want to be able to create, retrieve, update, and delete roles

  Background:
    Given There is a registered user with username "username" and password "password" and email "user@email.com"
    And I can login with username "username" and password "password"

  Scenario: Create a new role
    When I create a role with name "new_role"
    Then The response code is 201

  Scenario: Retrieve existing role details
    Given There is a role with name "existing_role"
    When I retrieve the role with name "existing_role"
    Then The response code is 200

  Scenario: Update existing role details
    Given There is a role with name "existing_role"
    When I update the role with name "existing_role" to have name "updated_role"
    Then The response code is 200

  Scenario: Delete existing role
    Given There is a role with name "role_to_delete"
    When I delete the role with name "role_to_delete"
    Then The response code is 200
    And There are 0 roles

  Scenario: Get non-existent role details
    When I retrieve the role with name "non_existent_role"
    Then The response code is 404

  Scenario: Update non-existent role
    When I update the role with name "non_existent_role" to have name "updated_role"
    Then The response code is 404

  Scenario: Delete non-existent role
    When I delete the role with name "non_existent_role"
    Then The response code is 404
