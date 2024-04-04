Feature: Register an Adoption
  In order to be able to adopt a Pet
  As a User
  I want to be able to adopt a Pet

  Background:
    Given There is a registered user with username "username" and password "password" and email "email@gmail.com"
    Given There is a registered user with username "username2" and password "password2" and email "email2@gmail.com"

  Scenario: User successfully adopts a Pet
    And I can login with username "username" and password "password"
    Given The Pet with id 1 exists
    Given The Pet with id 1 is not adopted
    When The user with username "username" adopts the Pet with id 1
    Then The Pet with id 1 should be marked as adopted

  Scenario: User tries to adopt a Pet that is already adopted
    And I can login with username "username" and password "password"
    And The Pet with id 1 exists
    And The Pet with id 1 is adopted by user "username2"
    When The user with username "username" adopts the Pet with id 1
    Then The system should display an error message indicating the Pet with id 1 is already adopted by another user "username2"

  Scenario: User tries to adopt a Pet that does not exist
    And I can login with username "username" and password "password"
    And The Pet with id 1 does not exists
    When The user with username "username" adopts the Pet with id 1
    Then The system should display an error message indicating the Pet with id 1 does not exist
