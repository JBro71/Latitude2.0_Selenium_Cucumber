@phone
Feature: vulnerabilities
  Feature file basic one person Vulnerabilites

  Background:
  Given I am logged into the Latitude Desktop

  @basicVulnerable @basicVulnerableTest1 
  Scenario: add an Email address and then a care and hardship record of type visual. check the correct batchapi and comms outputs.
  Given that I am running test "test1"
  
 	And that I have an account open in Latitude with the following details 
   #|FIELD NAME						|VALUE																	|M|NOTES																												|
		|Client								|$defaultClient													|	|																															|   
		|Stage								|Collections														|	|																															|
		|Household Size				|1																			|	|																															|
		
  Then I can amend "Preferred Contact Method" to "Email" for "$customerName,1"
   		
	And I can add an email address for "$customerName,1" 
   #|FIELD NAME						|VALUE																	|M|NOTES																												|
   	|Email Address				|debtor1email10@email.com								|X|																															|				
  	|Type									|Home																		|X|case sensitive																								|						
 		|Status								|Good																		|X|case sensitive																								|
		|Is Primary						|true																		|	|true/false																										|
		|Is Correspondence		|true																		|	|true/false																										|
		|Consent to Email			|true																		|	|true/false																										|
		|Obtained From				|$customerName,1												| |name of customer or $customerName,1 or 2	e.g.$customerName,1	|
		|Method								|written																|	|written/verbal																								|
		
	Then I can add a Care and Hardship record with the following details
	 #|FIELD NAME						|VALUE																	|M|NOTES																												|
	  |Customer							|$customerName,1												|X|name of customer or $customerName,1 or 2	e.g.$customerName,1	|
	 	|Care Type						|Visual																	|X|																															|
		|Financial Hardship		|IMPACT - Communicate										|X|																															|
	 	|Confirmed Care				|true																		|	|																															| 	
	  |Hold Days						|15																			|	|																															|	
	 	|Braile								|true																		|	|																															|
	 	
	Then I wait to run stage "2" until "2" "minutes" after the "first" stage
	
  Then I can search for a care record with the following details
	 #|FIELD NAME								|VALUE								|M|NOTES																																						|
	 	|Count										|1										| |Expected number of matching records																							|
	  |Customer									|$customerName,1			| |Case Sensitive name of customer or $customerName,1 or 2	e.g.$customerName,1			|
	 	|Care Type								|Visual								| |																																									|
		|Date Open								|$startDate,+0,dd/MM/yyyy	|	|date in format dd/MM/yyyy or delta from today in format $date,+N,dd/MM/yyyy 			|
	 	|Status										|											| |																																									|
		|Date Closed							|											|	|date in format dd/MM/yyyy or delta from today in format $date,+N,dd/MM/yyyy 			|
		
	Then I can check the selected Care and Hardship record contains the following details
	 #|FIELD NAME								|VALUE								|M|NOTES																																						|
	  |Hold Days								|15										|	|																																									|
	  |hold days approved				|true									|	|																																									|
	  |Expiration Date					|$startDate,+15,dd/MM/yyyy	|	|date in format dd/MM/yyyy or delta from today in format $date,+N,dd/MM/yyyy 			|	
	  	
	And I can search for a care record with the following details
	 #|FIELD NAME								|VALUE								|M|NOTES																																						|
	 	|Count										|1										| |Expected number of matching records																							|
	  |Customer									|$customerName,1			| |Case Sensitive name of customer or $customerName,1 or 2	e.g.$customerName,1			|
	 	|Care Type								|SpecialPref					| |																																									|
		|Date Open								|$startDate,+0,dd/MM/yyyy	|	|date in format dd/MM/yyyy or delta from today in format $date,+N,dd/MM/yyyy 			|
	 	|Status										|											| |																																									|
		|Date Closed							|											|	|date in format dd/MM/yyyy or delta from today in format $date,+N,dd/MM/yyyy 			|
		
	Then I can check the selected Care and Hardship record contains the following details
	 #|FIELD NAME								|VALUE								|M|NOTES																																						|	
		|Braile										|true									|	|																																									|
		|Large Type								|false								|	|																																									| 
 	 	|Audio File								|false								|	|																																									|	
 	 	
	 And I can check if a batchAPI entry has been created that matches these details
   #|FIELD NAME						|VALUE								|M|NOTES																																						|
    |Date Created					|$startDate,+0,dd/MM/yyyy	|	|date in format dd/MM/yyyy or delta from today in format $date,+N,dd/MM/yyyy 			|	
   	|Event								|Vulnerable						|	|																																									|
   	|Debtor ID						|$customerId,1				| |customerId or $customerId,1 or 2	e.g.$customerId,1																|
	
	And I can check if the batchAPI entry contains the following JSON values
   #|FIELD NAME						|VALUE								|M|NOTES																																						|	
 	  |FullName							|$customerName,1			|	|																																									|
 	  |CareType							|Visual								| |																																									|
 	  |HardshipType					|IMPACT - Communicate	| |																																									|
 	  |HoldDays							|15										| |																																									|
 	  

	Then I wait to run stage "3" until "1" "day" after the "first" stage
	
	And I can check if a communication was sent with the following details
   #|FIELD NAME				|VALUE												|M|NOTES																																						|
    |Method						|Email							 					|	|																																									|
   	|Code							|VUN01												|	|																																									|
   	|Date Requested		|$startDate,+0,dd/MM/yyyy			|	|date in format dd/MM/yyyy or delta from today in format $date,+N,dd/MM/yyyy			|  	
   	|Recipient				|$customerName,1							|	|																																									|
   	|Subject    			|An update about your account	| |name of customer or $customerName,1 or 2	e.g.$customerName,1											|
   	
	Then Test is complete
	
	###########################################################################################################################################
	
	
	 @basicVulnerable @basicVulnerableTest2
  Scenario: Add a care and hardship record of type Audio. check the correct batchapi and comms outputs.
  Given that I am running test "test2"
  
 	And that I have an account open in Latitude with the following details 
   #|FIELD NAME						|VALUE																	|M|NOTES																												|
		|Client								|$defaultClient													|	|																															|   
		|Stage								|Collections														|	|																															|
		|Household Size				|1																			|	|																															|
		
  Then I can amend "Preferred Contact Method" to "Letter" for "$customerName,1"
		
	Then I can add a Care and Hardship record with the following details
	 #|FIELD NAME						|VALUE																	|M|NOTES																												|
	  |Customer							|$customerName,1												|X|name of customer or $customerName,1 or 2	e.g.$customerName,1	|
	 	|Care Type						|Hearing																|X|																															|
		|Financial Hardship		|IMPACT - Communicate										|X|																															|
	 	|Confirmed Care				|true																		|	|																															| 	
	 	|Audio File						|true																		|	|																															|
	 	
	Then I wait to run stage "2" until "2" "minutes" after the "first" stage
	
  Then I can search for a care record with the following details
	 #|FIELD NAME								|VALUE								|M|NOTES																																						|
	 	|Count										|1										| |Expected number of matching records																							|
	  |Customer									|$customerName,1			| |Case Sensitive name of customer or $customerName,1 or 2	e.g.$customerName,1			|
	 	|Care Type								|Hearing							| |																																									|
		|Date Open								|$startDate,+0,dd/MM/yyyy	|	|date in format dd/MM/yyyy or delta from today in format $date,+N,dd/MM/yyyy 			|
	 	|Status										|											| |																																									|
		|Date Closed							|											|	|date in format dd/MM/yyyy or delta from today in format $date,+N,dd/MM/yyyy 			|
		
	Then I can check the selected Care and Hardship record contains the following details
	 #|FIELD NAME								|VALUE								|M|NOTES																																						|
	  |Hold Days								|30										|	|																																									|
	  |hold days approved				|true									|	|																																									|
	  |Expiration Date					|$startDate,+30,dd/MM/yyyy	|	|date in format dd/MM/yyyy or delta from today in format $date,+N,dd/MM/yyyy 			|	
	  	
	And I can search for a care record with the following details
	 #|FIELD NAME								|VALUE								|M|NOTES																																						|
	 	|Count										|1										| |Expected number of matching records																							|
	  |Customer									|$customerName,1			| |Case Sensitive name of customer or $customerName,1 or 2	e.g.$customerName,1			|
	 	|Care Type								|SpecialPref					| |																																									|
		|Date Open								|$startDate,+0,dd/MM/yyyy	|	|date in format dd/MM/yyyy or delta from today in format $date,+N,dd/MM/yyyy 			|
	 	|Status										|											| |																																									|
		|Date Closed							|											|	|date in format dd/MM/yyyy or delta from today in format $date,+N,dd/MM/yyyy 			|
		
	Then I can check the selected Care and Hardship record contains the following details
	 #|FIELD NAME								|VALUE								|M|NOTES																																						|	
		|Braile										|false								|	|																																									|
		|Large Type								|false								|	|																																									| 
 	 	|Audio File								|true									|	|																																									|	
 	 	
	 And I can check if a batchAPI entry has been created that matches these details
   #|FIELD NAME						|VALUE								|M|NOTES																																						|
    |Date Created					|$startDate,+0,dd/MM/yyyy	|	|date in format dd/MM/yyyy or delta from today in format $date,+N,dd/MM/yyyy 			|	
   	|Event								|Vulnerable						|	|																																									|
   	|Debtor ID						|$customerId,1				| |customerId or $customerId,1 or 2	e.g.$customerId,1																|
	
	And I can check if the batchAPI entry contains the following JSON values
   #|FIELD NAME						|VALUE								|M|NOTES																																						|	
 	  |FullName							|$customerName,1			|	|																																									|
 	  |CareType							|Hearing							| |																																									|
 	  |HardshipType					|IMPACT - Communicate	| |																																									|
 	  |HoldDays							|30										| |																																									|
 	  

	Then I wait to run stage "3" until "1" "day" after the "first" stage
	
	And I can check if a communication was sent with the following details
   #|FIELD NAME				|VALUE												|M|NOTES																																						|
    |Method						|Print							 					|	|case sensitive																																		|
   	|Code							|VUN01												|	|																																									|
   	|Date Requested		|$startDate,+0,dd/MM/yyyy			|	|date in format dd/MM/yyyy or delta from today in format $date,+N,dd/MM/yyyy			|  	
   	|Recipient				|$customerName,1							|	|																																									|
   	|Subject    			|$customerName,1							| |case sensitive																																		|
	Then Test is complete