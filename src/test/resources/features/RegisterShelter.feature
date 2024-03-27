Feature: Register Shelter
  In order to use the app
  As a user
  I want to register a Shelter

  Scenario: Register existing shelter name
    Given There is a registered shelter with name "existing_shelter", email "existing_shelter@gmail.com", mobile "+34 123 45 67 89" and isActive True
#    And I am a user with role "ShelterVolunteer"
    When I register a new shelter with name "existing_shelter", email "new_shelter@gmail.com", mobile "+34 567 56 56 56" and isActive True
    Then The response code is 409
    And It has not been created a shelter with name "existing_shelter"

  Scenario: Register shelter when already authenticated
    Given I am logged in as "ShelterAdmin" with email "admin@shelter.app" and password "adminpassword"
    When I register a new shelter with name "new_shelter", email "new_shelter@gmail.com", mobile "+34 567 56 56 56" and isActive True
    Then The response code is 403
    And It has not been created a shelter with name "new_shelter"

  Scenario: Register shelter with empty name
    Given I am a user with role "ShelterVolunteer"
    When I register a new shelter with name "", email "new_shelter@gmail.com", mobile "+34 567 56 56 56" and isActive True
    Then The response code is 400
    And The error message is "must not be blank"
    And It has not been created a shelter with name ""

  Scenario: Register shelter with empty email
    Given I am a user with role "ShelterVolunteer"
    When I register a new shelter with name "new_shelter", email "", mobile "+34 567 56 56 56" and isActive True
    Then The response code is 400
    And The error message is "must not be blank"
    And It has not been created a shelter with name "new_shelter"

  Scenario: Register shelter with invalid email
    Given I am a user with role "ShelterVolunteer"
    When I register a new shelter with name "new_shelter", email "invalid_email", mobile "+34 567 56 56 56" and isActive True
    Then The response code is 400
    And The error message is "must be a well-formed email address"
    And It has not been created a shelter with name "new_shelter"

  Scenario: Register shelter with empty mobile
    Given I am a user with role "ShelterVolunteer"
    When I register a new shelter with name "new_shelter", email "new_shelter@gmail.com", mobile "" and isActive True
    Then The response code is 400
    And The error message is "must not be blank"
    And It has not been created a shelter with name "new_shelter"

  Scenario: Register shelter with invalid mobile format
    Given I am a user with role "ShelterVolunteer"
    When I register a new shelter with name "new_shelter", email "new_shelter@gmail.com", mobile "invalid_mobile" and isActive True
    Then The response code is 400
    And The error message is "must be a valid mobile number"
    And It has not been created a shelter with name "new_shelter"

  Scenario: Register shelter with non-boolean isActive
    Given I am a user with role "ShelterVolunteer"
    When I register a new shelter with name "new_shelter", email "new_shelter@gmail.com", mobile "+34 567 56 56 56" and isActive "active"
    Then The response code is 400
    And The error message is "must be a boolean value"
    And It has not been created a shelter with name "new_shelter"

  Scenario: Register shelter with existing email
    Given There is a registered shelter with name "existing_shelter", email "existing_shelter@gmail.com", mobile "+34 123 45 67 89", and isActive True
    And I am a user with role "ShelterVolunteer"
    When I register a new shelter with name "new_shelter", email "existing_shelter@gmail.com", mobile "+34 567 56 56 56" and isActive True
    Then The response code is 409
    And I can login with email "existing_shelter@gmail.com" and password "existing_password"
