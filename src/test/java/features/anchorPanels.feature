Feature: Checking The Anchor panels
  
  Background:
  Given I am logged into the Latitude Desktop


  @tag1
  Scenario:  Checking the Account Status panel
    Given I have account "LB3383867165" open in Latitude
		And the "Account Overview" anchor panel will display a "Active Vulnerable" value of "No"
		And the "Customer Overview" anchor panel will display a "Primary Name" value of "Mr Cade Abbott MFA"
		And the "Contractual Due Information" anchor panel will display a "Billing Freq" value of "Monthly"
		