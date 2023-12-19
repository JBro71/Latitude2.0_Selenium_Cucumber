@tag
Feature: Sandbox
  Feature file for adding, amending or deleting 

  
	@updateAddress
  Scenario: find test data
  Given that I am running test "test1"
 	And that I have an account open in Latitude with the following details 
   #|FIELD NAME						|VALUE																	|M|NOTES																												|
		|Client								|$defaultClient													|	|																															|   
		|Stage								|Collections														|	|																															|
		|Household Size				|1																			|	|																															|
	Then I wait to run stage "2" until "10" "seconds" after the "last" stage
	Then I wait to run stage "3" until "10" "minutes" after the "last" stage	
	Then I wait to run stage "4" until "1" "hour" after the "first" stage
	Then I wait to run stage "5" until "10" "days" after the "last" stage
	Then Test is complete