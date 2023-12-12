Feature: Checking The Anchor panels
  
  Background:
  Given I am logged into the Latitude Desktop


  @anchor1
  Scenario:  Checking the Account Status panel
    Given I have account "LB3383867165" open in Latitude
		And the "Account Overview" anchor panel will display a "Active Vulnerable" value of "No"
		And the "Customer Overview" anchor panel will display a "Primary Name" value of "$customerName,1"
		And the "Contractual Due Information" anchor panel will display a "Billing Freq" value of "Monthly"
	#can use variables including $date,+N,FORMAT, $customerName,N
	
	
	
		  Scenario:  Checking the Account Status panel for multiple items
    Given I have account "LB3383867165" open in Latitude
		Then the anchor panels will display the following values
		|Account Overview							|Active Vulnerable		|No												|
		|Customer Overview						|Primary Name					|$customerName,1 					|
		|Contractual Due Information	|Billing Freq					|Monthly									|