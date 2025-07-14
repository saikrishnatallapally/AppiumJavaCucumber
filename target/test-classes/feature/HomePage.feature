Feature: Home Page Functionality

  @sanity @regression @hotel
  Scenario: Verify Hotels are displayed
    Given I am on the login page
    When I enter valid username
    And I enter valid password
    And I enter date of birth "01012000"
    And I click on login button
    Then user land on Home page
    Then verify Hotel Tab is displayed
    When click on Hotel Tab
    Then verify Hotel is displayed
