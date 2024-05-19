@disable
Feature: Leads

Background: To Login to the application
    Given the admin logs in to the application

Scenario: Create Leads
    When the admin creates a lead
    Then the admin verifies the lead information

Scenario: Create Organization
    When the admin creates a Organization
    Then the admin verifies the organization information
