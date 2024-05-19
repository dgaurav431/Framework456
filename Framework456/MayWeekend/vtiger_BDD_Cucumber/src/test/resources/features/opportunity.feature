@regression
Feature: Create Opportunity
	Scenario: To create an opportunity with Organization and Campaign Details
	
	Given The user launches the browser
	And The user logs in to the Application
	When The User creates an Organization 
	And The user creates a Campaign
	And The user creates an opportunity
	Then The user verifies the Opportunity Details