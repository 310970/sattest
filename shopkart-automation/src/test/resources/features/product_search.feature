@smoke @ui @api
Feature: Product search
  Scenario: Search returns matching products
    When a shopper searches products for "bag"
    Then the product results include "SKU-BAG"
