Feature: News and Video Feeds

  Scenario: Count video feeds in New & Features section
    Given I am on the Core Product home page
    When I navigate to News and Features Section
    Then I count the total number of video feeds
    And I count the video feeds that are older than 3 days