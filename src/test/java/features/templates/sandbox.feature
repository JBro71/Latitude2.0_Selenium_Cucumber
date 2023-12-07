@tag
Feature: Sandbox
  Feature file for adding, amending or deleting 

  Background:
  Given I am logged into the Latitude Desktop

	@findVulnerableRecord
  Scenario: Creating Care and Hardship records
  # Given  I have account "LB78012617239" open in Latitude
   Then I can search for a care record with the following details to check that it "does" exist
 #|FIELD NAME								|VALUE								|M|NOTES																																						|
 	|Count										|1										| |	Expected number of matching records																																								|
  |Customer									|Miss August Rowe			| |	Case Sensitive name of customer or $customerName,1 or 2	e.g.$customerName,1			|
 	|Care Type								|Prison								| |																																									|
	|Date Open								|06/12/2023						|	|	date in format dd/MM/YYYY or delta from today in format $date,+N,dd/MM/YYYY 		|
 	|Status										|       							| |																																									|
 	|Date Open								|06/12/2023						|	|	date in format dd/MM/YYYY or delta from today in format $date,+N,dd/MM/YYYY 		|