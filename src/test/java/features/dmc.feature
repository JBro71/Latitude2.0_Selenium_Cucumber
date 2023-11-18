
@tag
Feature: DMC
  Feature file for adding, amending or deleting DMC

  Background:
  Given I am logged into the Latitude Desktop

	@tag1
  Scenario:  Adding A DMC
   Given  I have account "A3EE80657316494ABC" open in Latitude
   Then I can "Add" a DMC with the following details
   |Customer				|2										|
   |company					|121 Debt Solutions		|
   |contact					|John Smith						|
   |client id				|123456								|
   |creditor id			|1234567							|
   |Date Accepted		|$-36							  	|
	 |Amount Accepted	|100									|
   |Comment					|DMC Comment			  	|
   
   #	@tag1
 # Scenario:  Updating A DMC
  # Given  I have account "PT68899261611" open in Latitude
  # Then I can "Update" a DMC with the following details
  # |Customer				|1										|
  # |company					|Abacus								|
  # |contact					|Jane Roberts					|
  # |client id				|654321								|
   #|creditor id			|7654321							|
   #|Date Accepted		|$-25							  	|
	# |Amount Accepted	|150									|
  # |Comment					|updated DMC Comment 	|
   
   
   
   
   
   
   