package testComponents;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import pageObjects.DPA;
import pageObjects.SearchPage;
import utils.Logging;

public class OpenAccount {

	WebDriver driver;
	PageUtils pageUtils;
	SearchPage searchPage;
	DPA dpa;
		
		public OpenAccount(WebDriver driver, PageUtils pageUtils, SearchPage searchPage, DPA dpa) { //initialise Webdriver in this class from the calling class
			//initialisation
			this.driver = driver;
			this.pageUtils = pageUtils;
			this.searchPage = searchPage;
			this.dpa = dpa;
		}
	
	
	
	public void OpenNewAccount(String accountNumber) throws Exception {
		//close existing account if one open
		String logEntryPrefix= "OpenAccount/OpenNewAccount/accountNumber: " +accountNumber+ ": " ; 
		Logging.logToConsole("DEBUG", logEntryPrefix + "Opening new account");
		
		pageUtils.CloseAccount();
		
		//**********************find and open new account******************
		searchPage.openAccount(accountNumber);
		
		//**********************dismiss DPA screen******************
		dpa.dismisDPA();
		pageUtils.closeAnchorPanel();
		int sleepTime = 100;		
		int totalSleepTime = 0;
		pageUtils.implictWait(0);
		//wait until the page is fully loaded by checking if this element is populated before doing anything
		for(int i=1;i<20 ;i++) { 
			Thread.sleep(sleepTime);
			totalSleepTime = totalSleepTime + sleepTime;
			Logging.logToConsole("DEBUG", "OpenAccount/OpenNewAccount: waiting for page to load");
			try {
				driver.findElement(By.xpath("//div[@ng-click='vm.displayAutoScript && vm.refreshFn()'][normalize-space()='Arrangements']")).click();
				if (driver.findElement(By.xpath("//input[@name='amount']")).getAttribute("value") != "") 
				{
					Logging.logToConsole("INFO", "OpenAccount/OpenNewAccount: waited " + totalSleepTime + " miliseconds for arrangement panel to fully load");
					//Thread.sleep(200);
					break;
				}
			}
			catch(Exception e)
				{
				Thread.sleep(200);
				}
		}
		pageUtils.defaultImplictWait();
	}
	
	
}
