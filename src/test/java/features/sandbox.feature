@tag
Feature: DMC
  Feature file for adding, amending or deleting DMC

  Background:
  Given I am logged into the Latitude Desktop

	@tag1
  Scenario:  Checking a DMC
   Given  I have account "A3EE80657316494ABC" open in Latitude
   Then I can "Check" a DMC with the following details
   |Customer				|2										|
   |company					|121 Debt Solutions		|
   |contact					|John Smith						|
   |client id				|123456								|
   |creditor id			|1234567							|
   |Date Accepted		|$-36							  	|
	 |Amount Accepted	|100.00								|
   |Comment					|DMC Comment			  	|