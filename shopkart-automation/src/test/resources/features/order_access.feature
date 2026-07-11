@api @negative @security
Feature: Orders are visible only to their owner
  Scenario: Another customer cannot read someone else's order
    Given "alice" has a PLACED order
    When "bob" requests GET /api/orders/{that id}
    Then the response status is 403
