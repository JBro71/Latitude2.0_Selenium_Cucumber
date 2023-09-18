package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

import utils.Logging;

public class DesktopVulnerabilites {
	
WebDriver driver;
	
	public DesktopVulnerabilites(WebDriver driver) { //initialise Webdriver in this class from the calling class
		//initialisation
		this.driver=driver;
	}
	
	public void OpenCareAndHardshipPanel() throws InterruptedException {
		Logging.logToConsole("INFO","**********************Open Care and Hardship Panel *************************");
		ArrangementScroll(300); //open the arrangement panel and scroll to it
		driver.findElement(By.xpath("//div[contains(text(),'Care-Financial Hardship')]")).click();//open arrangement panel

	}
	
	public void NewCareAndHardship(String careType, String harshipType) throws InterruptedException {
		
		Logging.logToConsole("INFO","**********************New Care and Hardship Record *************************");
		ArrangementScroll(300); //open the arrangement panel and scroll to it
		// dialogue does not always initialise the first time so the look is to work around that
		for(int i=1;i<3 ;i++) {
		try {
		driver.findElement(By.xpath("//button[normalize-space()='Add']")).click();//open arrangement panel
		driver.findElement(By.xpath("//input[@ng-model='vm.newCare.consent']")).click();//check consent check box
		Thread.sleep(500);
		Select careTypeDropdown =  new Select(driver.findElement(By.xpath("//select[@name='careType']")));
		careTypeDropdown.selectByVisibleText(careType);
		Select hardshipDropdown =  new Select(driver.findElement(By.xpath("//select[@name='hardshipType']")));
		hardshipDropdown.selectByVisibleText(harshipType);
		driver.findElement(By.xpath("//input[@name='vm.newCare.confirmed']")).click();//check confirmed care check box
		break;
		}
		catch (Exception e){
			Logging.logToConsole("ERROR","**********************New Care and Hardship Record ERROR retry *************************");
			driver.findElement(By.xpath("//button[@class='btn ng-binding ng-scope']")).click();//cancel 
			Thread.sleep(500);
		}
		}
		
	}
	
	public String CareHoldDays(String SetHoldDays) throws InterruptedException {		
		Logging.logToConsole("INFO","**********************Set Care and Hardship hold days *************************");
		String actualHoldDays = driver.findElement(By.xpath("//input[@name='holdDays']")).getAttribute("value");//hold days
		//det the actual hold days to the required value
		if (!SetHoldDays.equalsIgnoreCase("default")) {
			driver.findElement(By.xpath("//input[@name='holdDays']")).clear();
			driver.findElement(By.xpath("//input[@name='holdDays']")).sendKeys(SetHoldDays);
		}
		return actualHoldDays;
	}
	
	public boolean SubmitCareAndHardshipRecord() throws InterruptedException {		
		Logging.logToConsole("INFO","**********************Submit Care and hardship Record *************************");
		try {
		if (driver.findElement(By.xpath("//button[contains(text(),'Add')]")).isEnabled()) { //add button
			//String careType = driver.findElement(By.xpath("//select[@name='careType']")).getText();
			//Select careTypeDropdown =  new Select(driver.findElement(By.xpath("//select[@name='careType']")));
			//String careType = careTypeDropdown.getFirstSelectedOption().getText();
			driver.findElement(By.xpath("//button[contains(text(),'Add')]")).click();
			Thread.sleep(500);
			//String addEnabled = driver.findElement(By.xpath("//button[normalize-space()='Add']")).getAttribute("disabled");
			boolean editEnabled = driver.findElement(By.xpath("//button[contains(text(),'Edit')]")).isEnabled();
			//String careTypeCode = driver.findElement(By.xpath("//input[@name='careTypeCode']")).getAttribute("value");
			if (!editEnabled ) {//|| !careTypeCode.equals(careType) ) {
				Logging.logToConsole("INFO","**********************Submit Care Record Failed *************************");
				Logging.logToConsole("DEBUG","editEnabled: " + editEnabled );
				
				return false;
			}
			return true;
		}
		return false;
	} 
		catch (Exception e) {
			Logging.logToConsole("INFO","**********************Submit Care Record Errored *************************");
			Logging.logToConsole("ERROR","Error: " + e);
			return false;
	}
	}
	
	
	
	
	public void ArrangementScroll(int down) throws InterruptedException { 
		//scroll the screen to the section with the Arrangement panel
			JavascriptExecutor js = (JavascriptExecutor)driver;
			js.executeScript("window.scrollBy(0,-1000)");
			Thread.sleep(300);
			js.executeScript("window.scrollBy(0,"+down+")");						

	}
	
}
