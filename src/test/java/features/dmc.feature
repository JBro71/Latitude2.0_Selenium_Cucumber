
@DMC
Feature: DMC
  Feature file for adding, amending or deleting DMC

  Background:
  Given I am logged into the Latitude Desktop

	@addDmc
  Scenario:  Adding A DMC
   Given  I have account "A3EE80657316494ABC" open in Latitude
   Then I can "Add" a DMC with the following details
  #|FIELD NAME				|VALUE								|M|NOTES																																						|
   |Customer					|$customerName,2			|X|	Case Sensitive name of customer or $customerName,1 or 2	e.g.$customerName,1			|
   |company						|121 Debt Solutions		|X|																																									|
   |contact						|John Smith						|	|																																									|
   |client id					|123456								|	|																																									|
   |creditor id				|1234567							|	|																																									|
   |Date Accepted			|$date,-2,dd/MM/YYYY 	|	|date in format dd/MM/YYYY or delta from today in format $date,+N,dd/MM/YYYY  		|
	 |Amount Accepted		|100									|	|																																									|
   |Comment						|DMC Comment			  	|	|																																									|
   
   	@updateDmc
  Scenario:  Updating A DMC
   Given  I have account "A3EE80657316494ABC" open in Latitude
   Then I can "Update" a DMC with the following details
  #|FIELD NAME				|VALUE									|M|NOTES																																						|
   |Customer					|$customerName,2				|X|	Case Sensitive name of customer or $customerName,1 or 2	e.g.$customerName,1			|
   |company						|Abacus									|	|																																									|
   |contact						|Jane Roberts						|	|																																									|
   |client id					|654321									|	|																																									|
   |creditor id				|7654321								|	|																																									|
   |Date Accepted			|$date,-2,dd/MM/YYYY 		|	|date in format dd/MM/YYYY or delta from today in format $date,+N,dd/MM/YYYY  		|
	 |Amount Accepted		|150										|	|																																									|
   |Comment						|updated DMC Comment 		|	|																																									|
   
   @checkDmc
   Scenario:  Checking a DMC
   Given  I have account "A3EE80657316494ABC" open in Latitude
   Then I can "Check" a DMC with the following details
  #|FIELD NAME				|VALUE									|M|NOTES																																						|
   |Customer					|$customerName,1				|X|	Case Sensitive name of customer or $customerName,1 or 2	e.g.$customerName,1			|
   |company						|121 Debt Solutions			|	|																																									|
   |contact						|John Smith							|	|																																									|
   |client id					|123456									|	|																																									|
   |creditor id				|1234567								|	|																																									|
   |Date Accepted			|$date,-2,yyyy-MM-dd 		|	|																																									|
	 |Amount Accepted		|100.00									|	|																																									|
   |Comment						|DMC Comment wrong  		|	|																																									|
   
   @deleteDmc
  Scenario:  Delete A DMC
   Given  I have account "A3EE80657316494ABC" open in Latitude
   Then I can "Delete" a DMC with the following details
   #|FIELD NAME				|VALUE									|M|NOTES																																						|
   |Customer					|$customerName,1				|X|	Case Sensitive name of customer or $customerName,1 or 2	e.g.$customerName,1			|
   
   @addNewDebtManagementCompany
   Scenario:  Adding A Debt Management
   Given  I have account "A3EE80657316494ABC" open in Latitude
   Then I can "Add" a Debt Management Company with the following details
  #|FIELD NAME				|VALUE								|M|NOTES																																						|
   |Customer					|$customerName,2			|X|	Case Sensitive name of customer or $customerName,1 or 2	e.g.$customerName,1			|
   |company						|121 Debt Solutions		|X|																																									|
   |contact						|John Smith						|	|																																									|
   |client id					|123456								|	|																																									|
   |creditor id				|1234567							|	|																																									|
   |Date Accepted			|$date,-2,dd/MM/YYYY 	|	|date in format dd/MM/YYYY or delta from today in format $date,+N,dd/MM/YYYY  		|
	 |Amount Accepted		|100									|	|																																									|
   |Comment						|DMC Comment			  	|	|																																									|
   