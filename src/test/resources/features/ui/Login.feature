
Feature: login hotel application

  @Login @UI
  Scenario: 101:login to the hotel application
    Given Open Browser
    Given User is able Launch the hotel application
    When User enters the "kmanubolu" and "I9K36E" and Click LogIn button
	And LogOut application
