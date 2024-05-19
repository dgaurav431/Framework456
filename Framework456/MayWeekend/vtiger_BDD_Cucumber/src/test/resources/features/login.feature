Feature: Login

Scenario Outline: To Test Login Feature in Multiple Browsers

	Given Launch the <browser>
	When Navigate to the URL
	Then Verify Login Page
	
	Examples:
	|browser|
	|chrome|
	|firefox|
	|edge|
