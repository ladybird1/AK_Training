Feature: Checkout flows


  Scenario: Checkout Flow (one item)
    Given User logs in with default credentials
    When User adds products to cart
      | Sauce Labs Backpack |
    And User opens shopping cart
    Then There are items in shopping cart
      | Sauce Labs Backpack | 1 |
    When User goes through all order steps
    Then Message "Thank you for your order!" is visible


  Scenario: Checkout Flow (several items)
    Given User logs in with default credentials
    When User adds products to cart
      | Sauce Labs Backpack     |
      | Sauce Labs Bolt T-Shirt |
    And User remembers product prices
      | Sauce Labs Backpack     |
      | Sauce Labs Bolt T-Shirt |
    And User opens shopping cart
    Then There are items in shopping cart
      | Sauce Labs Backpack     | 1 |
      | Sauce Labs Bolt T-Shirt | 1 |
    When User completes order steps until Checkout review
    Then Order price is equal to sum of product prices
    When User clicks Finish order
    Then Message "Thank you for your order!" is visible