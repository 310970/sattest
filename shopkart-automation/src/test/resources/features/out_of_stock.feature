@api @negative
Feature: Stock protection
  Scenario: Adding more than available stock is rejected
    Given "alice" is authenticated
    When she adds 11 x "SKU-BAG" to a new cart
    Then the response status is 409
