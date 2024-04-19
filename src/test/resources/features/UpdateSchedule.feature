Feature: Update Schedule
  In order to update a schedule
  I want to update a new schedule

  Background:
    Given There is a registered user with username "username" and password "password" and email "user@domain.com"

  Scenario: Update a schedule with valid startTime and endTime
    Given I can login with username "username" and password "password"
    And There is a registered schedule with startTime "2024-03-17T08:00:00+01:00" and endTime "2024-03-17T21:00:00+01:00"
    When I update a schedule with startTime "2024-03-17T08:00:00+01:00" and endTime "2024-03-17T21:00:00+01:00" with new values startTime "2024-03-17T09:00:00+01:00" and endTime "2024-03-17T20:00:00+01:00"
    Then The response code is 200

  Scenario: Try to update a schedule without logging in
    Given I'm not logged in
    And There is a registered schedule with startTime "2024-03-17T08:00:00+01:00" and endTime "2024-03-17T21:00:00+01:00"
    When I update a schedule with startTime "2024-03-17T08:00:00+01:00" and endTime "2024-03-17T21:00:00+01:00" with new values startTime "2024-03-17T09:00:00+01:00" and endTime "2024-03-17T20:00:00+01:00"
    Then The response code is 401

  Scenario: Try to update a non-created schedule
    Given I can login with username "username" and password "password"
    When I update a schedule with startTime "2024-03-17T08:00:00+01:00" and endTime "2024-03-17T21:00:00+01:00" with new values startTime "2024-03-17T09:00:00+01:00" and endTime "2024-03-17T20:00:00+01:00"
    Then The response code is 404


  Scenario: Update a schedule with startTime higher than endTime
    Given I can login with username "username" and password "password"
    And There is a registered schedule with startTime "2024-03-17T08:00:00+01:00" and endTime "2024-03-17T21:00:00+01:00"
    When I update a schedule with startTime "2024-03-17T08:00:00+01:00" and endTime "2024-03-17T21:00:00+01:00" with new values startTime "2024-03-17T10:00:00+01:00" and endTime "2024-03-17T08:00:00+01:00"
    Then The response code is 500
