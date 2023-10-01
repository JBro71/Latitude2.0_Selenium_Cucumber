package testComponents;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

import utils.Logging;

public class PageUtils extends BaseTest {
	
	WebDriver driver;
	JavascriptExecutor js;
	
	public PageUtils(WebDriver driver) { // initialise Webdriver in this class from the calling class
		// initialisation
		this.driver = driver;
		js = (JavascriptExecutor)driver;
		
	}

	public void DefaultImplictWait() {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Integer.parseInt(prop.getProperty("implicitWait"))));
	}
	
	public void ImplictWait(int wait) {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(wait));
	}

	
	public void CloseAccount() {
		//close existing account if one open
	try 
	{
		ImplictWait(0);
		driver.findElement(By.xpath("//a[normalize-space()='Close Account']")).click();
		Logging.logToConsole("INFO", "Account Closed");
		//context.DefaultImplictWait();
	}
	catch(Exception e){
		Logging.logToConsole("DEBUG", "No Existing open account to close");
		DefaultImplictWait();
		}	
	}
	
	
	public void ReturnHome() throws InterruptedException {
		js.executeScript("window.scrollBy(0,-1000)");
	}
	
	public void Scroll(int move) throws InterruptedException { 
		//scroll the screen to the section with the Arrangement panel
			js.executeScript("window.scrollBy(0,-1000)");
			Thread.sleep(300);
			js.executeScript("window.scrollBy(0," + move + ")");						
	}
}
