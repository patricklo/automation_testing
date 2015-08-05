@siteSearchResultsPageTest

Feature: To test the search function 

I want to run a sample feature file.

Scenario: test search function 

Given Test suites are ready
When user try to search the keywords
|q|
|equity|
Then the related results should be pop up properly
Then the title should contain global infos
Then the footer should contain the global infos