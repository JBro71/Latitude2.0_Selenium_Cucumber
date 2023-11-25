@communications
Feature: Communications
  Feature file for checking what communications were sent out

  Background:
  Given I am logged into the Latitude Desktop

	@complaint @addComplaint
  Scenario:  checking for comms
   Given  I have account "SM65133721423" open in Latitude
   Then I can check if a communication was sent with the following details
    |Method						|Print							  			|
   	|Code							|COL31										|
   	
   

