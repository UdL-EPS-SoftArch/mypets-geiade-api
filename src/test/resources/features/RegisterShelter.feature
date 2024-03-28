Feature: Register Shelter
  In order to use the app
  As a user
  I want to register a Shelter

  Background:
    Given There is a registered user with username "username" and password "password" and email "user@email.com"

  Scenario: Register existing shelter email
    Given I can login with username "username" and password "password"
    #    And I am a user with role "ShelterVolunteer"
    Given There is a registered shelter with name "existing_shelter", email "existing_shelter@gmail.com", mobile "+34 123 45 67 89"
    When I register a new shelter with name "new_shelter", email "existing_shelter@gmail.com", mobile "+34 567 56 56 56" and isActive True
    Then The response code is 409

  Scenario: Register shelter with no permission
    Given I can login with username "username" and password "password"
#    And I am a user with role "User"
    When I register a new shelter with name "new_shelter", email "new_shelter@gmail.com", mobile "+34 567 56 56 56" and isActive True
    Then The response code is 403
    And It has not been created a shelter with name "new_shelter"

  Scenario: Register shelter with empty name
    Given I can login with username "username" and password "password"
#    Given I am a user with role "ShelterVolunteer"
    When I register a new shelter with name "", email "new_shelter@gmail.com", mobile "+34 567 56 56 56" and isActive True
    Then The response code is 400
    And The error message is "must not be blank"
    And It has not been created a shelter with name ""

  Scenario: Register shelter with empty email
    Given I can login with username "username" and password "password"
#    Given I am a user with role "ShelterVolunteer"
    When I register a new shelter with name "new_shelter", email "", mobile "+34 567 56 56 56" and isActive True
    Then The response code is 400
    And The error message is "must not be blank"
    And It has not been created a shelter with name "new_shelter"

  Scenario: Register shelter with invalid email
    Given I can login with username "username" and password "password"
#    Given I am a user with role "ShelterVolunteer"
    When I register a new shelter with name "new_shelter", email "invalid_email", mobile "+34 567 56 56 56" and isActive True
    Then The response code is 400
    And The error message is "must be a well-formed email address"
    And It has not been created a shelter with name "new_shelter"

  Scenario: Register shelter with empty mobile
    Given I can login with username "username" and password "password"
#    Given I am a user with role "ShelterVolunteer"
    When I register a new shelter with name "new_shelter", email "new_shelter@gmail.com", mobile "" and isActive True
    Then The response code is 400
    And The error message is "must not be blank"
    And It has not been created a shelter with name "new_shelter"

  Scenario: Register shelter with invalid mobile format
    Given I can login with username "username" and password "password"
#    Given I am a user with role "ShelterVolunteer"
    When I register a new shelter with name "new_shelter", email "new_shelter@gmail.com", mobile "invalid_mobile" and isActive True
    Then The response code is 400
    And It has not been created a shelter with name "new_shelter"

  Scenario: Register shelter with existing email
    Given I can login with username "username" and password "password"
#    And I am a user with role "ShelterVolunteer"
    Given There is a registered shelter with name "existing_shelter", email "existing_shelter@gmail.com", mobile "+34 123 45 67 89"
    When I register a new shelter with name "new_shelter", email "existing_shelter@gmail.com", mobile "+34 567 56 56 56" and isActive True
    Then The response code is 409
    And It has not been created a shelter with name "new_shelter"

  Scenario: Register shelter when I am not logged in
    Given I'm not logged in
    When I register a new shelter with name "new_shelter", email "new_shelter@gmail.com", mobile "+34 567 56 56 56" and isActive True
    Then The response code is 401
    And It has not been created a shelter with name "new_shelter"

  Scenario: Register shelter with valid attributes
    Given I can login with username "username" and password "password"
    When I register a new shelter with name "new_shelter", email "new_shelter@gmail.com", mobile "+34 567 56 56 56" and isActive True
    Then The response code is 201
    And It has been created a shelter with name "new_shelter", email "new_shelter@gmail.com", mobile "+34 567 56 56 56", the isActive is not returned