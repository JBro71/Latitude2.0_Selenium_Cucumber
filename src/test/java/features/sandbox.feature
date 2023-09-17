Feature: Checking The Anchor panels
  
  Background:
  Given I am logged into the Latitude Desktop


  @tag1
  Scenario:  Checking the Account Status panel
    Given I have account "LB3383867164" open in Latitude
		And the "Account Overview" anchor panel will display a "Active Vulnerable" value of "Yes - Relationship Breakup"
		And the "Customer Overview" anchor panel will display a "Primary Name" value of "Mr Cade Abbott"
		And the "Contractual Due Information" anchor panel will display a "Billing Freq" value of "Monthly"
		