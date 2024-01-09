@Notes
Feature: HouseholdNotes
  Feature file for adding or checking Household Notes

  Background:
  Given I am logged into the Latitude Desktop


	@householdNotes @addHouseholdNote
  Scenario:  Adding a Houshold Note
   Given  I have account "A1CY92635864413" open in Latitude
   Then I can add a household note with the following details
  #|FIELD NAME				|VALUE												|M|NOTES																																						|
   |Action Code				|CO														|X|	include only the code not the description																				|
   |Result Code				|PAY													|X|	include only the code not the description																				|
   |Comments					|New Comment									|	|																																									|
   |Private						|false												| | 																																								|
   
   
   @householdNotes @checkHouseholdNotes
  Scenario:  Finding a Houshold Note
   Given  I have account "A1CY92635864413" open in Latitude
   Then I can check for household notes matching the following details
  #|FIELD NAME				|VALUE												|M|NOTES																																						|
   |Count							|1														|	|	Number of Matches expected																											|
   |PagesToCheck			|3														|	|	The number of pages of comments to check																				|
   |Date							|$date,-1,dd/MM/yyyy					| |	will ignore the time element in the notes																				|
   |Account						|2314													|	|																																									|
   |User							|jbroad												| | 																																								|
   |Action						|ACT													| | 																																								|  
   |Result						|WORK													| | 																																								|  
   |Comments					|Account considered as Worked	| | 																																								| 
   |Private?					|false												| | 																																								| 
   