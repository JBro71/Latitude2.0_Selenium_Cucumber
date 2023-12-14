@tag
Feature: Sandbox
  Feature file for adding, amending or deleting 

  Background:
  Given I am logged into the Latitude Desktop

	@updateAddress
  Scenario: update an address
  Given  I have account "LB78012617239" open in Latitude				   
 	Then I can update address "1 high street" belonging to "$customerName,1" with the following "Manual" details
   #|FIELD NAME						|VALUE													|M|NOTES																												|
		|Active								|false														|	|true/false																										|
		|Primary							|false														|	|true/false																										|
		|Correspondence				|false														|	|true/false																										|
	

		