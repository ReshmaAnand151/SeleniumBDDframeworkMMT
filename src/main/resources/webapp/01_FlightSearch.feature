
Feature: 01_Login

  Scenario: Login into Makemytrip Application
    Given User is in signin page
    And Close Login into page
    When User selects onewaytrip
    Then User should be navigated into the Flight page successfully
    
  Scenario: Search for Flight
    Given User is in Flight search page
    And Enter From and To City
    When User Enters Date of travel
    Then Flight search results should be displayed
    
 #   Scenario Outline: Login into Amazon Application
 #   Given User is in signin page
 #   And "<User>" enter "<username>" and "<password>" in page
 #   When Clicks on signin button
 #   Then User should be logged into the page successfully
    

 #   Examples: 
 #     |User|username|password|
 #     |name1|test01 |1234 |
    #  |name2|test01 |1234 |
