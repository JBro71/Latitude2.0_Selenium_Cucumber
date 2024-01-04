@vulnerable
Feature: Latitude 2.0 Vulnerabilites
  Latitude 2.0 vulnerabilites

  @addVulnerable 
  Scenario: Creating Care and Hardship records
   Then I can convert field names to a string
  #|FIELD NAME						|VALUE													|M|NOTES																												|
   #|Address Owner				|																|x|																															|				
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
		
		