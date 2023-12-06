package pageObjects;

import java.util.HashMap;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import testComponents.PageUtils;

import utils.Logging;

public class DesktopVulnerabilites {
	
WebDriver driver;
PageUtils pageUtils;
String logEntryPrefix;
	
	public DesktopVulnerabilites(WebDriver driver, PageUtils pageUtils) { //initialise Webdriver in this class from the calling class
		//initialisation
		this.driver = driver;
		this.pageUtils = pageUtils;
	}
	
	public HashMap<String, String> careAndHardship(HashMap<String,String> paramsMap, String action) throws Exception  {
			String account = pageUtils.testMap.get("account");
			logEntryPrefix= "Account: "+account+" DesktopVulnerabilites/Care&Hardship/"+action+": "; 
			//try {
				Logging.logToConsole("INFO",logEntryPrefix+" Start");
				pageUtils.closeAnchorPanel();
				pageUtils.ReturnHome();
				pageUtils.defaultImplictWait();
				Select dropdown;

				OpenCareAndHardshipPanel();
				WebElement inputWebElement; // temp element to hold values in case statement
				
				//add button may be disabled and the care and hardship panel does not always open correctly first time so the mess below id to handle that 
				pageUtils.implictWait(0);
				int sleep = 300;
				String holdDaysXpath = "//input[@name='holdDays']";
				for(int i=0;i<3 ;i++) {
					careAndHardshipAddButton(logEntryPrefix);
					try {
					// panel sometimes does not initilise properly
					Thread.sleep(sleep);
					inputWebElement = driver.findElement(By.xpath(holdDaysXpath));
					inputWebElement.clear();
					inputWebElement.sendKeys("0");
					break;
					}
					catch (Exception e){
						Logging.logToConsole("ERROR",logEntryPrefix + ":  unable to open Add care record panel ERROR retry");
						try {
							driver.findElement(By.xpath("//button[@class='btn ng-binding ng-scope']")).click();//cancel 
							}catch (Exception f){}
						sleep = sleep + 200;
						}
					}
				pageUtils.implictWait(1);
				

				
				// order in which the data should be entered
				String[] entryOrder = {"have consent","care type","financial hardship","confirmed care","care proof requested",
						"care proof received","financial proof requested","financial proof received","hold days","status","comments",
						"braile","large type","audio file","prison name","prison number","sentence date","release date","prison informant"};	
				
				//iterate through the keys that have a value and execute them in correct entry order

				WebElement button; 
				String xpathVar;
				if(action.equals("add")){
					button = driver.findElement(By.xpath("//button[@ng-click='vm.add()']"));
					xpathVar = "newCare";
				}else {
					button = driver.findElement(By.xpath("//button[@ng-click='vm.update()']"));
					xpathVar = "toUpdate";
				}
				
				
				

				Boolean isSelected;
				for (String i : entryOrder) {
					String value = paramsMap.get(i);
					if (value != null )
					{ 
					Logging.logToConsole("DEBUG", logEntryPrefix+" Key: " +i+ " Value: "+ value);	
					switch (i) {
						case "have consent":
							if(action.equals("check")) {
								
							}else {
								pageUtils.updateCheckBox(value, "//input[@ng-model='vm."+xpathVar+".consent']");
								}
						 break; 
						case "care type":
							if (action.equals("check")) {
								
							}else {//ok
								Select careTypeDropdown =  new Select(driver.findElement(By.xpath("//select[@name='careType']")));
								careTypeDropdown.selectByVisibleText(value);
								}
						 break; 
						case "financial hardship":
							if (action.equals("check")) {
								
							}else {//ok
								Select careTypeDropdown =  new Select(driver.findElement(By.xpath("//select[@name='hardshipType']")));
								careTypeDropdown.selectByVisibleText(value);
								}
						 break; 
						case "confirmed care":
							if (action.equals("check")) {
								
							}else {
									pageUtils.updateCheckBox(value, "//input[@ng-model='vm."+xpathVar+".confirmed']");
									}
						 break; 
						case "care proof requested":
							if (action.equals("check")) {
								
							}else {
									pageUtils.updateCheckBox(value, "//input[@ng-model='vm."+xpathVar+".careProofRequested']");
									}
						 break; 							
						case "care proof received":
							if (action.equals("check")) {
								
							}else {
									pageUtils.updateCheckBox(value, "//input[@ng-model='vm."+xpathVar+".careProofReceived']");
									}
						 break; 
						case "financial proof requested":
							if (action.equals("check")) {
								
							}else {
									pageUtils.updateCheckBox(value, "//input[@ng-model='vm."+xpathVar+".hardshipProofRequested']");
									}
						 break; 
						case "financial proof received":
							if (action.equals("check")) {
								
							}else {
									pageUtils.updateCheckBox(value, "//input[@ng-model='vm."+xpathVar+".hardshipProofReceived']");
									}
						 break; 
						case "hold days":
							if (action.equals("check")) {
								
							}else {//ok
								inputWebElement =driver.findElement(By.xpath(holdDaysXpath));
								inputWebElement.clear();
								inputWebElement.sendKeys(value);
									}
						 break; 
						case "status":
							if(action.equals("check")) {
								
							}else {//OK
								Select careTypeDropdown =  new Select(driver.findElement(By.xpath("//select[@name='Status']")));
								careTypeDropdown.selectByVisibleText(value);
								}
						 break; 
						case "comments":
							if(action.equals("check")) {
								
							}else {
								driver.findElement(By.xpath("//textarea[@ng-model='vm."+xpathVar+".Comment']")).sendKeys(value);
								}
						 break; 
						case "braile":
							if (action.equals("check")) {
								
							}else {
									pageUtils.updateCheckBox(value, "//input[@ng-model='vm."+xpathVar+".braille']");
									}
						 break; 
						case "large type":
							if (action.equals("check")) {
								
							}else {
									pageUtils.updateCheckBox(value, "//input[@ng-model='vm."+xpathVar+".largePrint']");
									}
						 break; 
						case "audio file":
							if (action.equals("check")) {
								
							}else {
									pageUtils.updateCheckBox(value, "//input[@ng-model='vm."+xpathVar+".audioFiles']");
									}
						 break; 
						case "prison name":
							if(action.equals("check")) {
								
							}else {
								driver.findElement(By.xpath("(//div[@class='control-label ng-binding'][normalize-space()='Prison Name'])[1]/following-sibling::div/input")).sendKeys(value);
								}
						 break; 
						case "prison number":
							if(action.equals("check")) {
								
							}else {
								driver.findElement(By.xpath("//input[@ng-model='vm.selectedCareType.prisonNumber']")).sendKeys(value);
								}
						 break; 
						case "sentence date":
							if(action.equals("check")) {
								
							}else {
								driver.findElement(By.xpath(
										"(//div[@class='control-label ng-binding'][normalize-space()='Sentence Date'])[1]/following-sibling::div/input")).sendKeys(value);
								}
						 break; 
						case "release date":
							if(action.equals("check")) {
								
							}else {
								driver.findElement(By.xpath("(//div[@class='control-label ng-binding'][normalize-space()='Release Date'])[1]/following-sibling::div/input")).sendKeys(value);
								}
						 break; 
						case "prison informant":
							if(action.equals("check")) {
								
							}else {
								driver.findElement(By.xpath("(//div[@class='control-label ng-binding'][normalize-space()='Prison Informant'])[1]/following-sibling::div/input")).sendKeys(value);
								}
						 break; 

					
						}
					}
				}

							
				if (!action.equals("check")) {
					if (button.isEnabled() == true) {
						button.click();
						Logging.logToConsole("INFO",logEntryPrefix+" care record "+action+"ed");
						}
						else { 
							button = driver.findElement(By.xpath("//button[@ng-click='vm.close()']"));
							driver.switchTo().defaultContent();
							throw new Exception(logEntryPrefix+" unable to "+action+" care record. Button disabled");
							}
					}
				
				driver.switchTo().defaultContent();
				return paramsMap;
			}
		
	
	
	
	public void careAndHardshipAddButton(String logEntryPrefix) throws Exception {
		for(int i=0; i<5 ;i++) {
			try {
				driver.findElement(By.xpath("//button[normalize-space()='Add']")).click();
				return;
				}catch (Exception e){
					Logging.logToConsole("ERROR",logEntryPrefix + ":  unable to click add button retrying");
					Thread.sleep(200);}
			}
		throw new Exception(logEntryPrefix + ":  ERROR <add> button disabled");
	}
	
	
	
	
	
	
	
	
	
	
	public void OpenCareAndHardshipPanel() throws InterruptedException {
		Logging.logToConsole("INFO","DesktopVulnerabilites/OpenCareAndHardshipPanel: Start");
		pageUtils.closeAnchorPanel();
		driver.findElement(By.xpath("//div[contains(text(),'Care-Financial Hardship')]")).click();//open arrangement panel

	}
	
	public void NewCareAndHardship(String careType, String harshipType) throws Exception {
		Logging.logToConsole("INFO","DesktopVulnerabilites/NewCareAndHardshipPanel: Start");
		pageUtils.closeAnchorPanel();
		// dialogue does not always initialise the first time so the loop is to work around that
		for(int i=1;i<3 ;i++) {
		 WebElement buttonAdd = driver.findElement(By.xpath("//button[normalize-space()='Add']"));
			if(!buttonAdd.isEnabled()) {
				throw new Exception("DesktopVulnerabilites/NewCareAndHardship/"+pageUtils.testMap.get("account")+":  ERROR <add> button disabled");
			}
		try {
		buttonAdd.click();//open arrangement panel
		driver.findElement(By.xpath("//input[@ng-model='vm.newCare.consent']")).click();//check consent check box
		Thread.sleep(300);
		Select careTypeDropdown =  new Select(driver.findElement(By.xpath("//select[@name='careType']")));
		careTypeDropdown.selectByVisibleText(careType);
		Select hardshipDropdown =  new Select(driver.findElement(By.xpath("//select[@name='hardshipType']")));
		hardshipDropdown.selectByVisibleText(harshipType);
		driver.findElement(By.xpath("//input[@name='vm.newCare.confirmed']")).click();//check confirmed care check box
		break;
		}
		catch (Exception e){
			Logging.logToConsole("ERROR","DesktopVulnerabilites/NewCareAndHardshipPanel:  ERROR retry");
			driver.findElement(By.xpath("//button[@class='btn ng-binding ng-scope']")).click();//cancel 
			Thread.sleep(500);
		}
		}
		
	}
	
	public String CareHoldDays(String SetHoldDays) throws Exception {		
		Logging.logToConsole("INFO","DesktopVulnerabilites/CareHoldDays: Start");
		try {
			String actualHoldDays = driver.findElement(By.xpath("//input[@name='holdDays']")).getAttribute("value");//hold days
			//det the actual hold days to the required value
			if (!SetHoldDays.equalsIgnoreCase("default")) {
				driver.findElement(By.xpath("//input[@name='holdDays']")).clear();
				driver.findElement(By.xpath("//input[@name='holdDays']")).sendKeys(SetHoldDays);
			}
			return actualHoldDays;
		}
		catch (Exception e){
			throw new Exception("DesktopVulnerabilites/CareHoldDays/"+pageUtils.testMap.get("account")+": hold days ERROR (possible invalid care type");
		}
	}
	
	public boolean SubmitCareAndHardshipRecord() throws InterruptedException {		
		Logging.logToConsole("INFO","DesktopVulnerabilites/SubmitCareAndHardshipRecord:Start");
		WebElement addbutton = driver.findElement(By.xpath("//div[@class='modal-footer lat-form-idle']//button[contains(text(),'Add')]"));
		boolean editEnabled = driver.findElement(By.xpath("//button[contains(text(),'Edit')]")).isEnabled();
		for(int i=0; i<10; i++) {
			try {
				addbutton.click();	
				}catch(Exception e) {
					Logging.logToConsole("INFO",
						"DesktopVulnerabilites/NewCareAndHardship/"+pageUtils.testMap.get("account")+": add new Errored on attempt "+ (i+1) );
					}
			try {
				if (editEnabled ) {return true;}
				}catch(Exception e) {
					Logging.logToConsole("INFO",
					"DesktopVulnerabilites/NewCareAndHardship/"+pageUtils.testMap.get("account")+": submit new Errored on attempt "+ (i+1) );
				}
			Thread.sleep(100);
		}
		return false;
		
	
	}
	

}
