@vulnerable
Feature: Latitude 2.0 Vulnerabilites
  Latitude 2.0 vulnerabilites

  @addVulnerable 
  Scenario: Creating Care and Hardship records
   Then I can convert field names to a string
 #|FIELD NAME								|VALUE								|M|NOTES																																						|
   	|Phone Number					|07875544878										|X|																															|				
  	|Phone Extension			|1234														|X|case sensitive																								|						
 		|Type									|Home														|X|case sensitive																								|
		|Status								|Good														|	|true/false																										|
		|Name									|John Smith											|	|true/false																										|
		|on Hold							|true														| |name of customer or $customerName,1 or 2	e.g.$customerName,1	|
		|Hold Expiration Date	|01/02/2024											|	|written/verbal																								|
		|Is Correspondence		|true														|	|true/false																										|
		|Consent To Call			|true														|	|true/false																										|
		|Consent To Auto Dial	|true														|	|true/false																										|
		|Consent To SMS				|true														|	|true/false																										|
		|Consent To FAX				|true														|	|true/false																										|
		|Obtained From				|$customerName,1								|	|true/false																										|	
		|Method								|Verbal													|	|true/false																										|	
		|Comments							|this is a comment							|	|																															|					

