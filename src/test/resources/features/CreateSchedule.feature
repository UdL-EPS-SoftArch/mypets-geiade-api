Feature: Create Schedule
  In order to create a schedule
  I want to create a new schedule

  Background:
    Given There is a registered user with username "username" and password "password" and email "user@domain.com"
    # Aqui va la step del alsina: And There is

  Scenario: Create a new schedule with valid startTime and endTime
    Given I can login with username "username" and password "password"
    When I create a new schedule with startTime "2024-03-17T08:00:00+01:00" and endTime "2024-03-17T21:00:00+01:00" for shelter "shelter"
    Then The response code is 201
    And There is 1 schedule created
    And I try to retrieve that schedule
    And The response code is 200

  Scenario: Try to create a schedule without logging in
    Given I'm not logged in
    When I create a new schedule with startTime "2024-03-17T08:00:00+01:00" and endTime "2024-03-17T21:00:00+01:00" for shelter "shelter"
    Then The response code is 401
    And No schedule is created

  Scenario: Create a new schedule with startTime higher than endTime
    Given I can login with username "username" and password "password"
    When I create a new schedule with startTime "2024-03-17T10:00:00+01:00" and endTime "2024-03-17T08:00:00+01:00" for shelter "shelter"
    Then The response code is 400
    And No schedule is created
