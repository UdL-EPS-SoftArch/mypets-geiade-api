Feature: Register an Adoption
  In order to be able to adopt a Pet
  As a User
  I want to be able to adopt a Pet

  Background:
    Given There is a registered user with username "username" and password "password" and email "email"
    And There is a registered Pet with id "id" and isAdopted "isAdopted"

  Scenario: User successfully adopts a Pet
    Given The user is logged in with username "username" and password "password"
    And The Pet with id "id" exists and is not adopted
    When The user adopts the Pet with id "id"
    Then The Pet with id "id" should be marked as adopted

  Scenario: User tries to adopt a Pet that is already adopted
    Given The user is logged in with username "username" and password "password"
    And The Pet with id "id" exists and is adopted
    When The user tries to adopt the Pet with id "id"
    Then The system should display an error message indicating the Pet is already adopted

  Scenario: User tries to adopt a Pet without logging in
    Given The user is not logged in
    When The user tries to adopt the Pet with id "id"
    Then The system should prompt the user to log in

  Scenario: User tries to adopt a Pet that does not exist
    Given The user is logged in with username "username" and password "password"
    When The user tries to adopt a non-existing Pet with id "id"
    Then The system should display an error message indicating the Pet does not exist
