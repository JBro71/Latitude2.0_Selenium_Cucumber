@tag
Feature: Sandbox
  Feature file for adding, amending or deleting 

  Background:
  Given I am logged into the Latitude Desktop

	@findPhoneNumber
  Scenario:  Adding an phone number
  Given  I have account "LB78012617239" open in Latitude				   
	Then I can search for a phone number with following details
	 #|FIELD NAME										|VALUE													|M|NOTES																												|
		|Count												|1															| |																															|	
		|Number												|07184 248232										| |																															|				 
	 	|Type 												|Mobile													| |																															|				
		|Consent to Call							|false													| ||		
		|Consent to Auto Dial					|false													| |																															|	
		|Consent to Fax								|true														| |																															|							
		|Consent to Sms								|true														| |																															|	
		|Status												|true														| |true = good, false = bad																			|									
		|Customer											|Mrs Ciaran Colon								| |name of customer or $customerName,1 or 2	e.g.$customerName,1	|	
		|Attempts											|0															| |																															|	
		|Last Attempt									|																| |																															|	
		|Name													|																| |																															|	
		|Correspondence								|false													| |																															|	
		|on hold											|																| |																															|	
		|hold expiration							|																| |																															|	
 Then I can update the phone number above with following values
   #|FIELD NAME						|VALUE													|M|NOTES																												|
 		|Type									|Home														|X|case sensitive																								|
		|Status								|Good														|	|true/false																										|
		|Customer	on Account	|Mrs Ciaran Colon								| |name of customer or $customerName,1 or 2	e.g.$customerName,1	|	
		|Name									|pam														|	|true/false																										|
		|on Hold							|true														| |																															|
	 	|Hold Expiration Date	|01/02/2024											|	|date in format dd/MM/YYYY or delta from today in format $date,+N,dd/MM/YYYY																																		|
		|Is Correspondence		|true														|	|true/false																										|
		|Consent To Call			|true														|	|true/false																										|
		|Consent To Auto Dial	|true														|	|true/false																										|
		|Consent To SMS				|true														|	|true/false																										|
		|Consent To FAX				|true														|	|true/false																										|
		|Obtained From				|$customerName,1								|	|true/false																										|	
		|Method								|verbal													|	|written/verbal																								|	
		|Comments							|this is a comment							|	|																															|		