package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import testComponents.PageUtils;
import utils.Logging;

public class DPA {

	WebDriver driver;
	PageUtils pageUtils;
	
	
	public DPA(WebDriver driver, PageUtils pageUtils) { //initialise Webdriver in this class from the calling class
		//initialisation
		this.driver=driver;
		this.pageUtils = pageUtils;
	}
		
	public void dismisDPA() throws InterruptedException  {
			
			for(int z=1;z<10; z++) {
				//check if there has been a no match and if so break out to the outer loop

				pageUtils.implictWait(0);
				try {
					driver.findElement(By.xpath("//button[@class='btn btn-primary'][normalize-space()='Close']")).click();
					Logging.logToConsole("DEBUG", "DPA dismissed");
					pageUtils.defaultImplictWait();
					return;
				}catch (Exception e) {Logging.logToConsole("DEBUG", "DPANot found attempt: " + String.valueOf(z) ); }
				try {
					driver.findElement(By.xpath("(//div[contains(text(),'Repayment Types')])[1]")).click();
					pageUtils.defaultImplictWait();
					return;
				}catch (Exception e) {Logging.logToConsole("DEBUG", "No DPA Not found attempt: " + String.valueOf(z) ); }

				Thread.sleep(200);
			
				}
				pageUtils.defaultImplictWait();
				return;		
	}
}
