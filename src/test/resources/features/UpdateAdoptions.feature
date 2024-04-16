Feature: Update an Adoption
  In order to manage pet adoptions
  As a User
  I want to be able to update the adoption status of a Pet

  Background:
    Given There is a registered user with username "username" and password "password" and email "email@gmail.com"
    Given There is a registered user with username "username2" and password "password2" and email "email2@gmail.com"
    Given There is a registered user with username "username3" and password "password3" and email "email3@gmail.com"

    Scenario: User successfully updates the adoption User of a Pet
    Given I can login with username "username" and password "password"
    Given There is a registered pet with name "super_cat", date of birth "01-01-2024", isAdopted True, colour "black", sex "male", chip "1234", race "european", isDangerous False, at the shelter named "shelter1"
    Given The Pet with chip "1234" is adopted by user "username2"
    When Update the adoption with id 1 User with username "username3"
    Then The adoption with id 1 should have been updated to username "username3"
    And The adoption with id 1 dateofAdoption is updated

  Scenario: User successfully updates the adoption User of a Pet
    Given I can login with username "username" and password "password"
    Given There is a registered pet with name "super_cat", date of birth "01-01-2024", isAdopted True, colour "black", sex "male", chip "1234", race "european", isDangerous False, at the shelter named "shelter1"
    Given The Pet with chip "1234" is adopted by user "username2"
    When Update the adoption with id 1 User with username "username4"
    Then The system should display an error message indicating the username "username4" does not exist