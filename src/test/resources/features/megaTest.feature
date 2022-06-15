#Author: Tae-Hyung Kim
#Date: 13/06/2022
Feature: Mega Website Feature test

Background: user is logged in
    Given user is on MegaWebsite
    And enters login page
    When user enters username and password
    And click login button
    Then user is logged into Mega    

Scenario: Create test file on Mega
    When user right clicks on page
    And click New text file button
    And enter text file name
    And click Create
    And Enter file content
    And click Save
    And click close button
    Then file is saved on Mega drive

Scenario: Delete test file on Mega
    When user right clicks on test file
    And click Remove button
    Then file is removed on Mega drive
   
Scenario: Restore deleted file on Mega
		When user navigates to Rubbish bin page
		And select testfile
		And click Restore button
		Then file is restored on Cloud drive    