package pageObjects;

import java.time.Duration;
//import java.util.Properties;

//import java.net.URI;
//import java.util.function.Predicate;
import org.openqa.selenium.By;
//import org.openqa.selenium.HasAuthentication;
//import org.openqa.selenium.UsernameAndPassword;
import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.support.ui.ExpectedConditions;
//import org.openqa.selenium.WebElement;
//import org.openqa.selenium.support.ui.WebDriverWait;

import testComponents.BaseTest;
import utils.Logging;


public class LoginPage {
	
	WebDriver driver;
	
	public LoginPage(WebDriver driver) { //initialise Webdriver in this class from the calling class
		//initialisation
		this.driver=driver;
	}
	
	public void login () throws InterruptedException {
	
		Logging.logToConsole("DEBUG", "LoginPage/login: log in to Latitude");
		driver.get(BaseTest.prop.getProperty("LatitudeUrl"));
		driver.manage().window().maximize();


		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1));
		//click the login button repeatedly until next page appears (don't ask)
		for(int i=0;i<20; i++) {
			Logging.logToConsole("DEBUG", "LoginPage/login: logging in. Try No:" + i );
			try {
			driver.findElement(By.xpath("//a[contains(text(),'windows login')]")).click();
			} catch (Exception e) 
			{ }
			try {
			driver.findElement(By.xpath("//a[normalize-space()='Agent Desktop']")).getText();
			//wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[normalize-space()='Agent Desktop']")));
			} catch (Exception e) 
				{ 
				//Logging.logToConsole("DEBUG", "error: " +  e);
				Thread.sleep(500);
				continue;
				}
			break;
		}
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Integer.parseInt(BaseTest.prop.getProperty("implicitWait"))));
		Logging.logToConsole("DEBUG", "LoginPage/login: Login Complete");
	}
}
