package testComponents;

import java.util.HashMap;
//import java.util.Map;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
//import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
//import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;

//import com.fasterxml.jackson.core.type.TypeReference;
//import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;

//import pages.AccountPickerPage;
import pageObjects.DPA;
import pageObjects.DesktopAnchorPanels;
//import pageObjects.DesktopArrangements;
//import pages.LoginPage;
import pageObjects.SearchPage;
//import pages.SplashPage;
//import stepDefinitions.StepDefinition;
import utils.Logging;
//import utilities.Parameters;


public class BaseTest {

	public static Properties prop = new Properties ();
	public WebDriver driver;
	public static WebDriver staticDriver;
	//public DesktopArrangements arrangements;
	public DesktopAnchorPanels anchor;
	//public static desktopOpen;
	//list of all account numbers mapped to file numbers
	public static HashMap<String, String> fileNumbersMap = new HashMap<String, String>();
	//public values used in test
	public static HashMap<String, String> testMap = new HashMap<String, String>();

		
	public boolean initilizeDriver() throws IOException {
		//load the properties and output the key ones
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+ "//src//test//java//utils//params.properties");
		prop.load(fis);										
		Logging.logToConsole("INFO","LogLevel: " + BaseTest.prop.getProperty("logLevel"));
		Logging.logToConsole("INFO", "Browser: " + BaseTest.prop.getProperty("browser"));
		Logging.logToConsole("INFO", "Default Implicit Wait" + BaseTest.prop.getProperty("implicitWait"));
		
		//check if we already have a driver from previous iteration
		if (staticDriver != null) {
			driver = staticDriver;
			Logging.logToConsole("DEBUG", "Using Existing Driver");
			//InitialisePages();
			return true;
		}
		//load a list of all the file numbers		
		Filenumbers();

		//initialise the driver
		switch (prop.getProperty("browser").toLowerCase()) {
			case "edge":
				//EdgeOptions options = new EdgeOptions();
				System.setProperty("webdriver.edge.driver", "C:/Users/jbroad/Drivers/msedgedriver.exe");
				driver = new EdgeDriver();
				break;		
			case "firefox":	
				System.setProperty("webdriver.gecko.driver", "C:/Users/jbroad/Drivers/geckodriver.exe");
				driver = new FirefoxDriver();	
			default:
				System.setProperty("webdriver.chrome.driver", "C:/Users/jbroad/Drivers/chromedriver.exe");
				driver = new ChromeDriver();
		}
		staticDriver = driver;

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Integer.parseInt(prop.getProperty("implicitWait"))));
		Logging.logToConsole("DEBUG", "Start Test");
		InitialisePages();
		return false;
	
	}	
	
	public void InitialisePages() {
		DesktopAnchorPanels anchor = new DesktopAnchorPanels(driver);
		this.anchor = anchor;
	}
	
	public static void Filenumbers() throws IOException {
		String filePath = "//src//test//java//utils//";
		String fileFolder = "";
		String fileName ="filenumbers.txt";
		String rawFileNumbers = FileUtils.readFileToString(new File(System.getProperty("user.dir") + filePath + fileFolder + fileName ), StandardCharsets.UTF_8);	
		String[] fileNumbersArray = rawFileNumbers.split(System.lineSeparator());
		for (String value : fileNumbersArray) {
			String[] tempArray = value.split(",");
			fileNumbersMap.put(tempArray[0], tempArray[1]);
		}
		//Logging.logToConsole("INFO",fileNumbersMap.toString());
		
			}
	
	public void OpenNewAccount(String accountNumber) throws InterruptedException {
		//close existing account if one open
		try 
		{
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(0));
		driver.findElement(By.xpath("//a[normalize-space()='Close Account']")).click();
		Logging.logToConsole("INFO", "Account Closed");
		}
		catch(Exception e){}
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Integer.parseInt(prop.getProperty("implicitWait"))));
		
		//**********************find and open new account******************
		SearchPage searchPage = new SearchPage(driver);
		searchPage.openAccount(accountNumber);
		
		//**********************dismiss DPA screen******************
		DPA dpa = new DPA(driver);
		dpa.dismisDPA();
		
		//wait until the page is fully loaded by checking if this element is populated before doing anything
		for(int i=1;i<20 ;i++) { 
			Thread.sleep(500);
			Logging.logToConsole("DEBUG", "waiting for page to load");
			try {
				if (driver.findElement(By.xpath("//input[@name='amount']")).getAttribute("value") != "") 
				{
					Logging.logToConsole("INFO", "waited " + i*500 + " miliseconds for arrangement panel");
					Thread.sleep(500);
					break;
				}
			}
			catch(Exception e)
				{
				Thread.sleep(500);
				}
		}
	}
	
}
