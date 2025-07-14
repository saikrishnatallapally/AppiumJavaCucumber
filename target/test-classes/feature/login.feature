Feature: Login Functionality

  @sanity @regression
  Scenario: Successful login with valid credentials
    Given I am on the login page
    When I enter valid username
    And I enter valid password
    And I enter date of birth "01012000"
    And I click on login button
    Then user land on Home page