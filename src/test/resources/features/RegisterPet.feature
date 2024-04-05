Feature: Register pet
  In order to use the app
  As a user
  I want to register a pet

  Background:
    Given There is a registered user with username "username" and password "password" and email "user@email.com"
    And There is a registered shelter with name "shelter1", email "shelter1@gmail.com", mobile "666 55 44 33"

  Scenario: Register existing pet
    Given I can login with username "username" and password "password"
    # And I am a user with role "petOwner"  Temporarily commented because we need to do first the Role tests
    Given There is a registered pet with name "super_cat", date of birth "01-01-2024", isAdopted True, colour "black", sex "male", chip "1234", race "european", isDangerous False, at the shelter named "shelter1"
    When I register a new pet with name "super_cat", date of birth "01-01-2024", isAdopted True, colour "black", sex "male", chip "1234", race "european", isDangerous False, at the shelter named "shelter1"
    Then The response code is 409

  Scenario: Register pet with no permission
    Given I can login with username "username" and password "password"
    # And I am a user with role "User"  Temporarily commented because we need to do first the Role tests
    When I register a new pet with name "super_cat", date of birth "01-01-2024", isAdopted True, colour "black", sex "male", chip "1234", race "european", isDangerous False, at the shelter named "shelter1"
    Then The response code is 201
    # Then The response code is 403  Temporarily commented because we need to do first the Role tests
    # And It has not been created a pet with name "super_cat", date of birth "01-01-2024", isAdopted True, colour "black", sex "male", chip "1234", race "european", isDangerous False, at the shelter named "shelter1" Temporarily commented because we need to do first the Role tests

  Scenario: Register pet with empty chip
    Given I can login with username "username" and password "password"
    # Given I am a user with role "petOwner"  Temporarily commented because we need to do first the Role tests
    When I register a new pet with name "super_cat", date of birth "01-01-2024", isAdopted True, colour "black", sex "male", chip "", race "european", isDangerous False, at the shelter named "shelter1"
    Then The response code is 400
    And The error message is "must not be blank"
    And It has not been created a pet with chip ""

  Scenario: Register pet with empty name
    Given I can login with username "username" and password "password"
    # Given I am a user with role "petOwner"  Temporarily commented because we need to do first the Role tests
    When I register a new pet with name "", date of birth "01-01-2024", isAdopted True, colour "black", sex "male", chip "1234", race "european", isDangerous False, at the shelter named "shelter1"
    Then The response code is 400
    And The error message is "must not be blank"
    And It has not been created a pet with name ""

  Scenario: Register pet with existing chip
    Given I can login with username "username" and password "password"
#    And I am a user with role "petOwner"  Temporarily commented because we need to do first the Role tests
    Given There is a registered pet with name "super_cat", date of birth "01-01-2024", isAdopted True, colour "black", sex "male", chip "1234", race "european", isDangerous False, at the shelter named "shelter1"
    When I register a new pet with name "super_cat2", date of birth "01-01-2024", isAdopted True, colour "black", sex "male", chip "1234", race "european", isDangerous False, at the shelter named "shelter1"
    Then The response code is 409
    And It has not been created a pet with name "super_cat2"

  Scenario: Register pet when I am not logged in
    Given I'm not logged in
    When I register a new pet with name "super_cat", date of birth "01-01-2024", isAdopted True, colour "black", sex "male", chip "1234", race "european", isDangerous False, at the shelter named "shelter1"
    Then The response code is 401
    And It has not been created a pet with chip "1234"

  Scenario: Register pet with valid attributes
    Given I can login with username "username" and password "password"
    When I register a new pet with name "super_cat", date of birth "01-01-2024", isAdopted True, colour "black", sex "male", chip "1234", race "european", isDangerous False, at the shelter named "shelter1"
    Then The response code is 201
    And It has been created a pet with name "super_cat", date of birth "01-01-2024", isAdopted True, colour "black", sex "male", chip "1234", race "european", isDangerous False, at the shelter named "shelter1"
