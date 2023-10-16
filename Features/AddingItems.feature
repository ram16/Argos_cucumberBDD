#Feature: List of scenarios.
#Scenario: Business rule through list of steps with arguments.
#Given: Some precondition step
#When: Some key actions
#Then: To observe outcomes or validation
#And,But: To enumerate more Given,When,Then steps
#Scenario Outline: List of steps for data-driven as an Examples and <placeholder>
#Examples: Container for s table
#Background: List of steps run before each of the scenarios

Feature: Searching and Adding Products to the Basket on Argos

 @sanity
  Scenario: Search for a product, add it to the basket,Increase the quantity by 2 , and verify
    Given User Launch browser
    And opens URL "https://www.argos.co.uk/"
    When the user searches for a product
    Then the search result page should return the item
    When the user adds the product to the basket
    Then the product should be in the basket
    When the user increases the quantity of the product by 2
    Then the products should reflect the increased quantity

