package utils;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import testComponents.BaseTest;
import pageObjects.*;


public class Context extends BaseTest {

	private DesktopVulnerabilites desktopVulnerabilites;
	private LoginPage loginPage;
	private SearchPage searchPage;
	private SplashPage splashPage;
	private DesktopAnchorPanels desktopAnchorPanels;

	
	public Context() throws IOException
	{
		if (driver==null) {
		initilizeDriver();
		}
	}
	
	
	public WebDriver getDriver()
	{
		return driver;
	}
	
	public DesktopVulnerabilites getDesktopVulnerabilites()
	{
		if(desktopVulnerabilites==null)
		{
			desktopVulnerabilites = new DesktopVulnerabilites(driver);
		}
		return desktopVulnerabilites;
	} 
	
	public LoginPage getLoginPage()
	{
		if(loginPage==null)
		{
			loginPage = new LoginPage(driver);
		}
		return loginPage;
	} 	
	
	public SplashPage getSplashPage()
	{
		if(splashPage==null)
		{
			splashPage = new SplashPage(driver);
		}
		return splashPage;
	} 	
	
	
	public SearchPage getSearchPage()
	{
		if(searchPage==null)
		{
			searchPage = new SearchPage(driver);
		}
		return searchPage;
	} 	
	
	public DesktopAnchorPanels getDesktopAnchorPanels()
	{
		if(desktopAnchorPanels==null)
		{
			desktopAnchorPanels = new DesktopAnchorPanels(driver);
		}
		return desktopAnchorPanels;
	} 	
}