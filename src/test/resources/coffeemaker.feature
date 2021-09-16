Feature: Purchase Beverage with makecoffee()

Scenario: pay exact price
    Given I purchase a beverage
    When I pay 50
    Then I get 0 change

Scenario: pay more than price
    Given I purchase a beverage
    When I pay 100
    Then I get 50 change

Scenario: not enough ingredients
    Given I purchase a beverage
    When There are not enough ingredients
    And I pay 100
    Then I get 100 change

Scenario: not enough money paid
    Given I purchase a beverage
    When I pay 20
    Then I get 20 change
