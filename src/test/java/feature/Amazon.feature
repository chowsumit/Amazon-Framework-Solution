Feature: Amazon scenarios

Scenario: Login to Amazon shopping website
Given User select the "Chrome" Browser  
And User navigate to "www.amazon.com" URL
When User logins into amazon application with username "Test@test.com" and "Test134" password
Then Users name "Test" is displayed in the account panel

Scenario: User search an item from the website
Given Users name "Test" is displayed in the account panel  
When User search the item "Teddy Bear" in the search area
Then All "Teddy Bear" item is displayed in the search result

Scenario: User modify the search result according to the filter criteria
Given All "Teddy Bear" item is displayed in the search result  
When User sorts the result by selecting "Avg. Customer Review"
And User selects the Age range "5 to 7 Years" old
Then All desired items are displayed in the search result

Scenario: User selects the First item and add that to cart
Given All desired items are displayed in the search result  
When User clicks and navigate to the number 1 item
Then User adds the item to the cart 
And User navigate back to the search page

Scenario: User selects tttonhe Second item and add that to cart
Given All desired items are displayed in the search result  
When User clicks and navigate to the number 2 item
Then User adds the item to the cart 
And User navigate back to the search page

Scenario: User validates the items in the cart
Given All desired items are displayed in the search result
When User navigates to the cart
Then User validates the number 1 item in the cart
And User validates the number 2 item in the cart


   






