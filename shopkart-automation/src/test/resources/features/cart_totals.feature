@api @db
Feature: Cart totals
  Scenario: Cart total is the sum of quantity times price in paise
    Given "alice" is authenticated
    When she adds 2 x "SKU-BAG" to a new cart
    Then the cart total is 99800 paise
