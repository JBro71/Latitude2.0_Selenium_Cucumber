@tag
Feature: Sandbox
  Feature file for adding, amending or deleting 

  Background:
  Given I am logged into the Latitude Desktop

	@checkVulnerable 
  Scenario: Creating Care and Hardship records
  Given  I have account "LB78012617239" open in Latitude
  Then I can check the selected Care and Hardship record contains the following details
 #|FIELD NAME								|VALUE								|M|NOTES																																						|
  |Customer									|Miss August Rowe			|X|	Case Sensitive name of customer or $customerName,1 or 2	e.g.$customerName,1			|
 	|Care Type								|Prison								|X|																																									|
	|Financial Hardship				|Debt Moratorium			|X|																																									|
 	|Have Consent							|true									|	|																																									|
 	|Confirmed Care						|true									|	|																																									| 
 	|times in care						|											|	|																																									|
 	|Care Proof Required			|true									|	|																																									| 			
 	|Care Proof Requested			|true									|	|																																									| 	
 	|Care Proof Received			|true									|	|																																									| 
 	|Financial Proof Required	|true									|	|																																									| 
 	|Financial Proof Requested|true									|	|																																									| 
  |Financial Proof Received	|true									|	|																																									|	 		
  |Hold Days								|10										|	|																																									|
  |hold days approved				|true									|	|																																									|
  |Expiration Date					|12/12/2024						|	|date in format dd/MM/YYYY or delta from today in format $date,+N,dd/MM/YYYY 			|		
 	|Status										|Proof Confirmed			|	|																																									|
 	|Closed Date							|											|	|date in format dd/MM/YYYY or delta from today in format $date,+N,dd/MM/YYYY 			|			
 	|Comments									|new comment					|	|																																									|
 	|Braile										|false								|	|																																									|
 	|Large Type								|false								|	|																																									| 
  |Audio File								|true									|	|																																									|	
  |Prison Name							|Slade								|	|																																									|	 
  |Prison Number						|123456								|	|																																									|	
  |Sentence Date						|01/01/2223						|	|date in format dd/MM/YYYY or delta from today in format $date,+N,dd/MM/YYYY 			|	
  |Release Date							|12/12/2024						|	|date in format dd/MM/YYYY or delta from today in format $date,+N,dd/MM/YYYY 			|	
  |Prison Informant					|No										|	|																																									|	