Feature: Register an Adoption
  In order to be able to adopt a Pet
  As a User
  I want to be able to adopt a Pet

  Background:
    Given There is a registered user with username "username" and password "password" and email "email@gmail.com"
    Given There is a registered user with username "username2" and password "password2" and email "email2@gmail.com"

  Scenario: User successfully adopts a Pet
    Given I can login with username "username" and password "password"
    Given There is a registered pet with name "cat", date of birth "10-10-2023", isAdopted False, colour "white", sex "male", chip "1234", race "european", isDangerous False, at the shelter named "shelter1"
    Given The Pet with chip "1234" is not adopted
    When The user with username "username" adopts the Pet with chip "1234"
    Then The Pet with chip "1234" should be marked as adopted

  Scenario: User tries to adopt a Pet that is already adopted
    Given I can login with username "username" and password "password"
    Given There is a registered pet with name "super_cat", date of birth "01-01-2024", isAdopted True, colour "black", sex "male", chip "1234", race "european", isDangerous False, at the shelter named "shelter1"
    Given The Pet with chip "1234" is adopted by user "username2"
    When The user with username "username" adopts the Pet with chip "1234"
    Then The system should display an error message indicating the Pet with chip "1234" is already adopted by another user "username2"

  Scenario: User tries to adopt a Pet that does not exist
    Given I can login with username "username" and password "password"
    Given The Pet with chip "9876" does not exists
    When The user with username "username" adopts the Pet with chip "9876"
    Then The system should display an error message indicating the Pet with chip "9876" does not exist
