package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import testComponents.PageUtils;

import utils.Logging;

public class DesktopVulnerabilites {
	
WebDriver driver;
PageUtils pageUtils;
	
	public DesktopVulnerabilites(WebDriver driver, PageUtils pageUtils) { //initialise Webdriver in this class from the calling class
		//initialisation
		this.driver = driver;
		this.pageUtils = pageUtils;
	}
	
	public void OpenCareAndHardshipPanel() throws InterruptedException {
		Logging.logToConsole("INFO","DesktopVulnerabilites/OpenCareAndHardshipPanel: Start");
		pageUtils.Scroll(300);
		driver.findElement(By.xpath("//div[contains(text(),'Care-Financial Hardship')]")).click();//open arrangement panel

	}
	
	public void NewCareAndHardship(String careType, String harshipType) throws Exception {
		
		Logging.logToConsole("INFO","DesktopVulnerabilites/NewCareAndHardshipPanel: Start");
		pageUtils.Scroll(300);  //open the arrangement panel and scroll to it
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
		try {
		if (driver.findElement(By.xpath("//button[contains(text(),'Add')]")).isEnabled()) { //add button
			driver.findElement(By.xpath("//button[contains(text(),'Add')]")).click();
			Thread.sleep(500);
			boolean editEnabled = driver.findElement(By.xpath("//button[contains(text(),'Edit')]")).isEnabled();
			if (!editEnabled ) {//|| !careTypeCode.equals(careType) ) {
				Logging.logToConsole("INFO","DesktopVulnerabilites/SubmitCareAndHardshipRecord: Failed ");
				Logging.logToConsole("DEBUG","DesktopVulnerabilites/SubmitCareAndHardshipRecord: editEnabled: " + editEnabled );
				return false;
			}
			return true;
		}
		return false;
	} 
		catch (Exception e) {
			Logging.logToConsole("INFO","DesktopVulnerabilites/SubmitCareAndHardshipRecord: Error");
			Logging.logToConsole("ERROR","Error: " + e);
			return false;
	}
	}
	

}
