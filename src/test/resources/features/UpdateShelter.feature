Feature: Update Shelter
  In order to maintain shelter information
  As a user with appropriate permissions
  I want to be able to update a shelter

  Background:
    Given There is a registered user with username "username" and password "password" and email "user@email.com"

  Scenario: Update existing shelter
    Given I can login with username "username" and password "password"
    And There is a registered shelter with name "existing_shelter", email "existing_shelter@gmail.com", mobile "+34 123 45 67 89"
    When I update the shelter with name "existing_shelter" and new attributes:
      | email                   | mobile             | isActive    |
      | new_email@gmail.com     | +34 567 56 56 56   | True        |
    Then The response code is 200

  Scenario: Update non-existent shelter
    Given I can login with username "username" and password "password"
    When I update the shelter with name "non_existent_shelter" and new attributes:
      | email                   | mobile             | isActive    |
      | new_email@gmail.com     | +34 567 56 56 56   | True        |
    Then The response code is 404

  Scenario: Update shelter with invalid email format
    Given I can login with username "username" and password "password"
    And There is a registered shelter with name "existing_shelter", email "existing_shelter@gmail.com", mobile "+34 123 45 67 89"
    When I update the shelter with name "existing_shelter" and new attributes:
      | email            |
      | invalid_email    |
    Then The response code is 400
    And The error message is "must be a well-formed email address"

  Scenario: Update shelter with invalid mobile format
    Given I can login with username "username" and password "password"
    And There is a registered shelter with name "existing_shelter", email "existing_shelter@gmail.com", mobile "+34 123 45 67 89"
    When I update the shelter with name "existing_shelter" and new attributes:
      | mobile           |
      | invalid_mobile   |
    Then The response code is 400

  Scenario: Update shelter when I am not logged in
    Given I'm not logged in
    And There is a registered shelter with name "existing_shelter", email "existing_shelter@gmail.com", mobile "+34 123 45 67 89"
    When I update the shelter with name "existing_shelter" and new attributes:
      | email                   | mobile             | isActive    |
      | new_email@gmail.com     | +34 567 56 56 56   | True        |
    Then The response code is 401