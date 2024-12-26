Feature: Get all products from the api

  @Smoke1
  Scenario: Verify the get api for the products
    Given I hit the url of get products api endpoint
    When I pass the url of products in the request
    Then I receive the response code as 200

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