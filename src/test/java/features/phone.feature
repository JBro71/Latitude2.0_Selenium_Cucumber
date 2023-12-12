@phone
Feature: phone
  Feature file for adding, amending, deleting or checking phone

  Background:
  Given I am logged into the Latitude Desktop


	@addPhone
  Scenario:  Adding an phone number
  Given  I have account "LB78012617239" open in Latitude				   
  Then I can add an phone number for "$customerName,1" 
   #|FIELD NAME						|VALUE													|M|NOTES																												|
   	|Phone Number					|07875544885										|X|																															|				
  	|Phone Extension			|1235														|X|case sensitive																								|						
 		|Type									|Home														|X|case sensitive																								|
		|Status								|Good														|	|true/false																										|
		|Name									|pam														|	|true/false																										|
		|Is Correspondence		|true														|	|true/false																										|
		|Consent To Call			|true														|	|true/false																										|
		|Consent To Auto Dial	|true														|	|true/false																										|
		|Consent To SMS				|true														|	|true/false																										|
		|Consent To FAX				|true														|	|true/false																										|
		|Obtained From				|$customerName,1								|	|true/false																										|	
		|Method								|verbal													|	|written/verbal																								|	
		|Comments							|this is a comment							|	|																															|		
	
		
  @findPhoneNumber
  Scenario:  Adding an phone number
  Given  I have account "LB78012617239" open in Latitude				   
	Then I can search for a phone number with following details
	 #|FIELD NAME										|VALUE													|M|NOTES																												|
		|Count												|1															| |																															|	
		|Number												|07184 248232										| |																															|				 
	 	|Type 												|Mobile													| |																															|				
		|Consent to Call							|false													| ||		
		|Consent to Call via dialer		|false													| |																															|	
		|Consent to Fax								|true														| |																															|							
		|Consent to Sms								|true														| |																															|	
		|Status												|true														| |true = good, false = bad																			|									
		|Customer											|Mrs Ciaran Colon								| |name of customer or $customerName,1 or 2	e.g.$customerName,1																																|	
		|Attempts											|0															| |																															|	
		|Last Attempt									|																| |																															|	
		|Name													|																| |																															|	
		|Correspondence								|false													| |																															|	
		|on hold											|																| |																															|	
		|hold expiration							|																| |																															|	
  
		
		
		
		