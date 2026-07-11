@api @negative
Feature: Cancelling orders
  Scenario: A placed order may be cancelled only once
    Given "alice" has a PLACED order
    When she cancels that order twice
    Then the first response is 200 and the second is 409
