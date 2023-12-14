@address
Feature: address
  Feature file for adding, amending, or checking address's

  Background:
  Given I am logged into the Latitude Desktop

@addPAFAddress
  Scenario:  Adding a PAF address
  Given  I have account "LB78012617239" open in Latitude				   
  Then I can add a "PAF" address for "$customerName,1" 
   #|FIELD NAME						|VALUE													|M|NOTES																												|
   #|Address Owner				|																|x|																															|				
  	|Type of Address			|Home														|X|case sensitive																								|						
		|Status of Address		|Good														|	|																															|
		|Source								|Customer												|	|																															|
		|Confirmation					|Customer												|	|																															|
		|Active								|true														|	|true/false																										|
		|Primary							|true														|	|true/false																										|
		|Correspondence				|true														|	|true/false																										|
		|Post Code						|SA1 1WD												|	|																															|
		|Address Line 1				|Flat 9 Monmouth House					|	|																															|	


	@addManualAddress
  Scenario:  Adding a Manual address
  Given  I have account "LB78012617239" open in Latitude				   
  Then I can add a "Manual" address for "$customerName,1" 
   #|FIELD NAME						|VALUE													|M|NOTES																												|
   #|Address Owner				|																|x|																															|				
  	|Type of Address			|Home														|X|																															|						
		|Status of Address		|Good														|	|																															|
		|Source								|Customer												|	|																															|
		|Confirmation					|Customer												|	|																															|
		|Active								|true														|	|true/false																										|
		|Primary							|true														|	|true/false																										|
		|Correspondence				|true														|	|true/false																										|
		|Post Code						|SA1 1WD												|	|																															|
		|Address Line 1				|1 high street									|	|																															|	
		|Address Line 2				|Mannheim Quay									|	|																															|	
		|City / Town					|Swansea												|	|																															|	
		|County								|West Glamorgan									|	|																															|	
		|Post Code						|SA1 1WD												|	|																															|				
		|Country							|United Kingdom									|	|																															|	
		
		
	 Scenario:  Search for an address
  Given  I have account "LB78012617239" open in Latitude				   
	Then I can search for an address with following details
	 #|FIELD NAME						|VALUE													|M|NOTES																												|
  	|Count								|1															|	|																															|			 
  	|Party								|$customerName,2								|	|																															|						
		|Type									|HOME														|	|																															|
		|Flags								|A															|	|																															|		
		|Status								|GOOD														|	|																															|		
		|Source								|CUSTOMER												|	|																															|
		|Confirmation Source	|CUSTOMER												|	|																															|
		|Address Line 1				|Flat 14 Monmouth House					|	|																															|	
		|Address Line 2				|Mannheim Quay									|	|																															|	
		|City/Town						|Swansea												|	|																															|	
		|County								|West Glamorgan									|	|																															|	
		|Post Code						|SA1 1WD												|	|																															|		
		|Country							|United Kingdom									|	|																															|	

  
  	
	@findAndUpdateAddress
  Scenario:  Seach for and update an address
  Given  I have account "LB78012617239" open in Latitude				   
	Then I can search for an address with following details
	 #|FIELD NAME						|VALUE													|M|NOTES																												|
		|Party								|$customerName,2								|	|																															|						
		|Type									|Work														|	|																															|
		|Address Line 1				|Flat 26 Monmouth House					|	|																															|	
 Then I can update the address above with following "Manual" values
   #|FIELD NAME						|VALUE													|M|NOTES																												|
		|Type of Address			|Work														|	|																															|						
		|Status of Address		|Bad														|	|																															|
		|Source								|Client													|	|																															|
		|Confirmation					|Cliente												|	|																															|
		|Active								|false													|	|true/false																										|
		|Primary							|false													|	|true/false																										|
		|Correspondence				|false													|	|true/false																										|
		|Address Line 1				|Flat 26 Monmouth House					|	|																															|	
		|Post Code						|SA2 1WD												|	|																															|				 	


		
	@updateAddress
  Scenario: update an address
  Given  I have account "LB78012617239" open in Latitude				   
 	Then I can update address "40 emperton road" belonging to "$customerName,1" with the following "Manual" details
   #|FIELD NAME						|VALUE													|M|NOTES																												|
		|Type of Address			|Home														|X|case sensitive																								|						
		|Status of Address		|Good														|	|																															|
		|Source								|Customer												|	|																															|
		|Confirmation					|Customer												|	|																															|
		|Active								|true														|	|true/false																										|
		|Primary							|true														|	|true/false																										|
		|Correspondence				|true														|	|true/false																										|
		|Address Line 1				|Flat 9 Monmouth House					|	|																															|	
		|Address Line 2				|Mannheim Quay									|	|																															|	
		|City / Town					|Swansea												|	|																															|	
		|County								|West Glamorgan									|	|																															|	
		|Post Code						|SA1 1WD												|	|																															|				
		|Country							|United Kingdom									|	|																															|	 
 
		
		