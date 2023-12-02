package testComponents;

import java.util.HashMap;
import java.util.Map;
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
	public static boolean firstTest = true;
	//public DesktopArrangements arrangements;
	public DesktopAnchorPanels anchor;
	//public static desktopOpen;
	//list of all account numbers mapped to file numbers
	public static HashMap<String,String[]> fileNumbersMap = new HashMap<String, String[]>();
	//public values used in test
	public HashMap<String, String> testMap = new HashMap<String, String>();
	public static HashMap<String, String> staticTestMap = new HashMap<String, String>();

	
	public void initilizeDriver() throws IOException {
		//load the properties and output the key ones
		
		if (staticDriver == null) { 		// if the browser is not already open
		InitiliseProperties(); 			//load the properties file
		staticTestMap.put("account", ""); 		//Initialise the account value;
		staticTestMap.put("customer1", null);
		staticTestMap.put("customer2", null);
		staticTestMap.put("customerId1", null);
		staticTestMap.put("customerId2",null);
	    //copy the values from the Static Map
		for (Map.Entry<String, String> entry : staticTestMap.entrySet()) {
			testMap.put(entry.getKey(), entry.getValue());
	        }
		Filenumbers(); 					//load a list of all the file numbers	
		
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

		return;
		}
		
		Logging.logToConsole("DEBUG", "BaseTest/initilizeDriver: Using Existing Driver");
		firstTest = false;
		driver = staticDriver;
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Integer.parseInt(prop.getProperty("implicitWait"))));
	    //copy the values from the Static Map
		for (Map.Entry<String, String> entry : staticTestMap.entrySet()) {
			testMap.put(entry.getKey(), entry.getValue());
	        }
	}	
	
	
	public void InitiliseProperties() throws IOException {
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+ "//src//test//java//utils//params.properties");
		prop.load(fis);										
		Logging.logToConsole("INFO","LogLevel: " + BaseTest.prop.getProperty("logLevel"));
		Logging.logToConsole("INFO", "Browser: " + BaseTest.prop.getProperty("browser"));
		Logging.logToConsole("INFO", "Default Implicit Wait" + BaseTest.prop.getProperty("implicitWait"));
	}
	

	public static void Filenumbers() throws IOException {
		String filePath = "//src//test//java//utils//";
		String fileFolder = "";
		String fileName ="filenumbers.txt";
		String rawFileNumbers = FileUtils.readFileToString(new File(System.getProperty("user.dir") + filePath + fileFolder + fileName ), StandardCharsets.UTF_8);	
		String[] fileNumbersArray = rawFileNumbers.split(System.lineSeparator());
		for (String value : fileNumbersArray) {
			String[] tempArray = value.split(",");
			String[] tempArray2 = {"","",""};
			for (int i=0;i<3;i++) {tempArray2[i] = tempArray[i+1];}
			String tempKey = tempArray[0];
			String[] tempValue = tempArray2;		
			fileNumbersMap.put(tempArray[0], tempArray2);
		}
		Logging.logToConsole("INFO", "debug");
		//Logging.logToConsole("INFO",fileNumbersMap.toString());
		
			}
	
	/*
	public void OpenNewAccount(String accountNumber) throws InterruptedException {
		//close existing account if one open
		try 
		{
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(0));
		driver.findElement(By.xpath("//a[normalize-space()='Close Account']")).click();
		Logging.logToConsole("INFO", "OpenNewAccount: Account Closed");
		}
		catch(Exception e){}
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Integer.parseInt(prop.getProperty("implicitWait"))));
		
		//**********************find and open new account******************
		SearchPage searchPage = new SearchPage(driver);
		searchPage.openAccount(accountNumber);
		
		//**********************dismiss DPA screen******************
		PageUtils pageUtils = new PageUtils(driver);
		DPA dpa = new DPA(driver, pageUtils);
		dpa.dismisDPA();
		
		//wait until the page is fully loaded by checking if this element is populated before doing anything
		for(int i=1;i<20 ;i++) { 
			Thread.sleep(500);
			Logging.logToConsole("DEBUG", "baseTest/OpenNewAccount: waiting for page to load");
			try {
				if (driver.findElement(By.xpath("//input[@name='amount']")).getAttribute("value") != "") 
				{
					Logging.logToConsole("INFO", "basetest/OpenNewAccount: waited " + i*500 + " miliseconds for arrangement panel");
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
	*/
}
