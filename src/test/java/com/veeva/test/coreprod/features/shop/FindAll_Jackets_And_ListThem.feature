Feature: Jacket Shopping
  Scenario: Save all details of jackets in to a file.
    Given I am on the Core Product home page
    When I navigate to the Shop Menus Mens category
    Then I should find all jackets across all paginated pages
    And I store each jacket's price, title, and top seller message in a text file
