Feature: WareHouse Api Validation


  @sc-200
  Scenario: Verify Success Scenario
    Given I hit the authentication request with correct user details i get response code as 201
    When I hit default warehouse api with the generated access token form token api
    Then I receive the response code as 200
    And I validate schema and mandatory fields

  @sc-401
  Scenario: Verify Authentication Failed Scenario
    Given I hit the authentication request with correct user details i get response code as 201
    When I hit default warehouse api with the incorrect access token form token api
    Then I receive the response code as 401

  @sc-404
  Scenario: Verify 404 Not Found Scenario
    Given I hit the authentication request with correct user details i get response code as 201
    When I hit default warehouse api with the generated access token form token api but with incorrect endpoint
    Then I receive the response code as 404