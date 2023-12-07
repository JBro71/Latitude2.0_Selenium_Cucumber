@vulnerable
Feature: Latitude 2.0 Vulnerabilites
  Latitude 2.0 vulnerabilites

	Background:
  	Given I am logged into the Latitude Desktop

  @addVulnerable 
  Scenario: Creating Care and Hardship records
   Given  I have account "LB78012617239" open in Latitude
   Then I can "Add" a Care and Hardship record with the following details
 #|FIELD NAME								|VALUE								|M|NOTES																																						|
  |Customer									|Miss August Rowe			|X|	Case Sensitive name of customer or $customerName,1 or 2	e.g.$customerName,1			|
 	|Care Type								|Prison								|X|																																									|
	|Financial Hardship				|Debt Moratorium			|X|																																									|
 	|Have Consent							|true									|	|																																									|
 	|Confirmed Care						|true									|	|																																									| 	
 	|Care Proof Requested			|true									|	|																																									| 	
 	|Care Proof Received			|true									|	|																																									| 
 	|Financial Proof Requested|true									|	|																																									| 
  |Financial Proof Received	|true									|	|																																									|	 		
  |Hold Days								|10										|	|																																									|	
 	|Status										|Proof Confirmed			|	|																																									|	
 	|Comments									|new comment					|	|																																									|
 	|Braile										|true									|	|																																									|
 	|Large Type								|true									|	|																																									| 
  |Audio File								|true									|	|																																									|	
  |Prison Name							|Slade								|	|																																									|	 
  |Prison Number						|123456								|	|																																									|	
  |Sentence Date						|01/01/2223						|	|date in format dd/MM/YYYY or delta from today in format $date,+N,dd/MM/YYYY 			|	
  |Release Date							|12/12/2024						|	|date in format dd/MM/YYYY or delta from today in format $date,+N,dd/MM/YYYY 			|	
  |Prison Informant					|No										|	|																																									|	

	@findVulnerableRecord
  Scenario: Creating Care and Hardship records
   Given  I have account "LB78012617239" open in Latitude
   Then I can search for a care record with the following details to check that it "does" exist
 #|FIELD NAME								|VALUE								|M|NOTES																																						|
 	|Count										|1										| |	Expected number of matching records																																								|
  |Customer									|Miss August Rowe			| |	Case Sensitive name of customer or $customerName,1 or 2	e.g.$customerName,1			|
 	|Care Type								|Prison								| |																																									|
 	|Financial Hardship				|Debt Moratorium			| |																																									|
	|Date Open								|06/12/2023						|	|	date in format dd/MM/YYYY or delta from today in format $date,+N,dd/MM/YYYY 		|
 	|Status										|       							| |																																									|
 	|Date Closed							|06/12/2023						|	|	date in format dd/MM/YYYY or delta from today in format $date,+N,dd/MM/YYYY 		|









 # @simpleVulnerable 
  #Scenario Outline: Creating bulk Care and Hardship records
    #Given I have account "<acc_no>" open in Latitude
   # When I add a new C&H record with care type of "<careType>" and hardship of "<hardshipType>"
   # And the default hold days of "<holdDefault>" are set to "<holdActual>"
   # Then when I submit the C&H record it is stored successfully

    #Examples: 
      #|acc_no       |status      | careType                |hardshipType             |holdDefault|holdActual|
			#|PT68899261611 |Collections |Physical Difficulty      |IMPACT - Pay             |30      |Default |
      #|ME45929534221 |Collections |Physical Difficulty      |IMPACT - Pay             |30      |Default |
      #|FR9426427158 |Prearrears  |Long Term    					  |IMPACT - Pay             |30      |Default |
			#|KW15349141071|Recoveries  |Hearing					        |IMPACT - Communicate     |30      |Default |
			#|NL40CMIL7271 |Collections |Mental Health            |IMPACT - Communicate     |30      |Default |
			#|GB52ATOZ8516 |Prearrears  |Drug Addiction           |IMPACT - Manage          |30      |Default |
			#|LB31360825321|Recoveries  |Low Mental Capacity      |IMPACT - Manage          |30      |Default |
			#|AT3951285285 |Collections |Disability               |IMPACT - Pay             |30      |Default |
			#|FR1006425328 |Prearrears  |Retirement               |IMPACT - Pay             |30      |Default |
			#|CY92635864411|Recoveries  |Bereavement              |IMPACT - Communicate     |30      |Default |
			#|GL8844234719 |Collections |Relationship Breakup     |IMPACT - Communicate     |30      |Default |
			#|CR3663874638 |Prearrears  |Domestic abuse           |IMPACT - Manage          |30      |Default |
			#|BA49076361561|Recoveries  |CaringResponsibility     |IMPACT - Manage          |30      |Default |
			#|RO66SSSO6629 |Collections |Other                    |IMPACT - Pay             |30      |Default |
			#|IE92JVOQ7214 |Prearrears  |Dementia                 |IMPACT - Pay             |30      |Default |
			#|MU78742037011|Recoveries  |COVID-19                 |IMPACT - Communicate     |30      |Default |
			#|BG81EWRN3477 |Collections |Debt Moratorium          |IMPACT - Communicate     |999     |Default |
			#|MD1902829048 |Prearrears  |Emotional Resilience     |IMPACT - Manage          |30      |Default |
			#|RO15JQPD85681|Recoveries  |PoorLiteracy/Numeric     |IMPACT - Manage          |30      |Default |
			#|RO48YKTF5550 |Collections |Poor EnglishLanguage     |IMPACT - Pay             |30      |Default |
			#|HU7918280414 |Prearrears  |Poor Digital Skills      |IMPACT - Pay             |30      |Default |
			#|BE91610454341|Recoveries  |LearningDifficulties     |IMPACT - Communicate     |30      |Default |
			#|MR2417986695 |Prearrears  |Suicide                  |IMPACT - Manage          |90      |Default |
			#|LB8122937163 |Collections |NoConsent                |IMPACT - Communicate     |30      |Default |
			#|SI87726036251|Recoveries  |TerminalIllness          |IMPACT - Manage          |30      |Default |
			#|KZ7759206332 |Collections |AgeRelated               |IMPACT - Pay             |30      |Default |
			#|CH5861095798 |Prearrears  |Gambling Addiction       |IMPACT - Pay             |30      |Default |
			#|ME83576496561|Recoveries  |Armed Forces             |IMPACT - Communicate     |30      |Default |
			#|BG03PFLD76173|Collections |Alcohol Addiction       |IMPACT - Communicate     |30      |Default |
			#|FR94264271583|Prearrears  |Family Breakdown        |IMPACT - Pay   					 |30      |Default |
			#|LI15777227333|Collections |Speech Impairment       |IMPACT - Pay     					|30      |Default |
			#|KW16477266634|Recoveries  |Child Birth             |IMPACT - Manage     			|30      |Default |
			#|SK62416369794|Recoveries  |Addiction	             |IMPACT - Manage     			|30      |Default |
			#|LI1658040124 |Prearrears  |Severe	                  |IMPACT - Manage          |30      |Default |
      #|MT72EGEJ3321 |Collections |Visual								    |IMPACT - Communicate     |30      |Default |
			#|GB29SRQE2599 |Collections |IMPACT - Communicate     |Inadequate Income        |30      |Default |
			#|SE6265576570 |Prearrears  |IMPACT - Manage          |Over Indebtedness        |30      |Default |
			#|CY08656250321|Recoveries  |IMPACT - Manage          |Low Savings              |30      |Default |
			#|IT608LHRLI35 |Collections |IMPACT - Pay             |Managing Finances        |30      |Default |
			#|NL86GSCR7878 |Prearrears  |IMPACT - Pay             |No Help/Support          |30      |Default |
			#|VG78639642021|Recoveries  |IMPACT - Communicate     |Debt Moratorium          |60      |Default |
			#|BG03PFLD7617 |Collections |IMPACT - Communicate     |Income Shock             |30      |Default |
			#|BA6076166244 |Prearrears  |IMPACT - Manage          |SeekFinAssist            |30      |Default |
			#|LV45TOBS67461|Recoveries  |IMPACT - Manage          |BreathingSpace           |30      |Default |
			#|LI1577722733 |Collections |IMPACT - Pay             |ShortTermHold            |14      |Default |
			#|MC6523719245 |Prearrears  |IMPACT - Pay             |Cost of Living           |30      |Default |
			#|LB34495170531|Recoveries  |IMPACT - Communicate     |Unemployed - No Bens     |30      |Default |
			#|RS08737388333 |Collections |IMPACT - Communicate     |Unemployed - Benefit     |30      |Default |
			#|GT0638261153 |Prearrears  |IMPACT - Manage          |Redundancy               |30      |Default |
			#|GB52ATOZ85163 |Prearrears  |IMPACT - Manage          |Statutory Moratorium     |180   |Default |
			#|MT72EGEJ33213 |Collections  |IMPACT - Manage          |Victim of Fraud        |30      |Default | 


			#|FR9426427158 |Prearrears  |Long Term    					  |IMPACT - Pay             |30      |Default |
			#|KW15349141071|Recoveries  |Hearing					        |IMPACT - Communicate     |30      |Default |
			#|NL40CMIL7271 |Collections |Mental Health            |IMPACT - Communicate     |30      |Default |
			#|GB52ATOZ8516 |Prearrears  |Drug Addiction           |IMPACT - Manage          |30      |Default |
			#|LB31360825321|Recoveries  |Low Mental Capacity      |IMPACT - Manage          |30      |Default |
			#|AT3951285285 |Collections |Disability               |IMPACT - Pay             |30      |Default |
			#|FR1006425328 |Prearrears  |Retirement               |IMPACT - Pay             |30      |Default |
			#|CY92635864411|Recoveries  |Bereavement              |IMPACT - Communicate     |30      |Default |
			#|GL8844234719 |Collections |Relationship Breakup     |IMPACT - Communicate     |30      |Default |
			#|CR3663874638 |Prearrears  |Domestic abuse           |IMPACT - Manage          |30      |Default |
			#|BA49076361561|Recoveries  |CaringResponsibility     |IMPACT - Manage          |30      |Default |
			#|RO66SSSO6629 |Collections |Other                    |IMPACT - Pay             |30      |Default |
			#|IE92JVOQ7214 |Prearrears  |Dementia                 |IMPACT - Pay             |30      |Default |
			#|MU78742037011|Recoveries  |COVID-19                 |IMPACT - Communicate     |30      |Default |
			#|BG81EWRN3477 |Collections |Debt Moratorium          |IMPACT - Communicate     |999     |Default |
			#|MD1902829048 |Prearrears  |Emotional Resilience     |IMPACT - Manage          |30      |Default |
			#|RO15JQPD85681|Recoveries  |PoorLiteracy/Numeric     |IMPACT - Manage          |30      |Default |
			#|RO48YKTF5550 |Collections |Poor EnglishLanguage     |IMPACT - Pay             |30      |Default |
			#|HU7918280414 |Prearrears  |Poor Digital Skills      |IMPACT - Pay             |30      |Default |
			#|BE91610454341|Recoveries  |LearningDifficulties     |IMPACT - Communicate     |30      |Default |
			#|MR2417986695 |Prearrears  |Suicide                  |IMPACT - Manage          |90      |Default |
			#|LB8122937163 |Collections |NoConsent                |IMPACT - Communicate     |30      |Default |
			#|SI87726036251|Recoveries  |TerminalIllness          |IMPACT - Manage          |30      |Default |
			#|KZ7759206332 |Collections |AgeRelated               |IMPACT - Pay             |30      |Default |
			#|CH5861095798 |Prearrears  |Gambling Addiction       |IMPACT - Pay             |30      |Default |
			#|ME83576496561|Recoveries  |Armed Forces             |IMPACT - Communicate     |30      |Default |
			#|BG03PFLD76173|Collections |Alcohol Addiction       |IMPACT - Communicate     |30      |Default |
			#|FR94264271583|Prearrears  |Family Breakdown        |IMPACT - Pay   					 |30      |Default |
			#|LI15777227333|Collections |Speech Impairment       |IMPACT - Pay     					|30      |Default |
			#|KW16477266634|Recoveries  |Child Birth             |IMPACT - Manage     			|30      |Default |
			#|SK62416369794|Recoveries  |Addiction	             |IMPACT - Manage     			|30      |Default |
			#|LI1658040124 |Prearrears  |Severe	                  |IMPACT - Manage          |30      |Default |
      #|MT72EGEJ3321 |Collections |Visual								    |IMPACT - Communicate     |30      |Default |
			#|GB29SRQE2599 |Collections |IMPACT - Communicate     |Inadequate Income        |30      |Default |
			#|SE6265576570 |Prearrears  |IMPACT - Manage          |Over Indebtedness        |30      |Default |
			#|CY08656250321|Recoveries  |IMPACT - Manage          |Low Savings              |30      |Default |
			#|IT608LHRLI35 |Collections |IMPACT - Pay             |Managing Finances        |30      |Default |
			#|NL86GSCR7878 |Prearrears  |IMPACT - Pay             |No Help/Support          |30      |Default |
			#|VG78639642021|Recoveries  |IMPACT - Communicate     |Debt Moratorium          |60      |Default |
			#|BG03PFLD7617 |Collections |IMPACT - Communicate     |Income Shock             |30      |Default |
			#|BA6076166244 |Prearrears  |IMPACT - Manage          |SeekFinAssist            |30      |Default |
			#|LV45TOBS67461|Recoveries  |IMPACT - Manage          |BreathingSpace           |30      |Default |
			#|LI1577722733 |Collections |IMPACT - Pay             |ShortTermHold            |14      |Default |
			#|MC6523719245 |Prearrears  |IMPACT - Pay             |Cost of Living           |30      |Default |
			#|LB34495170531|Recoveries  |IMPACT - Communicate     |Unemployed - No Bens     |30      |Default |
			#|RS08737388333 |Collections |IMPACT - Communicate     |Unemployed - Benefit     |30      |Default |
			#|GT0638261153 |Prearrears  |IMPACT - Manage          |Redundancy               |30      |Default |
			#|GB52ATOZ85163 |Prearrears  |IMPACT - Manage          |Statutory Moratorium     |180   |Default |
			#|MT72EGEJ33213 |Collections  |IMPACT - Manage          |Victim of Fraud        |30      |Default | 
