package com.croydon;

import java.security.InvalidParameterException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

public class WebDriverHelper 
{
	public static WebDriver createDriver(String browser)
	{
		WebDriver webDriver=null;
		
		if(browser.equalsIgnoreCase("IE")){
			System.setProperty("webdriver.ie.driver", "C:\\Data\\chromedriver.exe");
			webDriver = new InternetExplorerDriver();
		  	webDriver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			
		}else if(browser.equalsIgnoreCase("Chrome")){
			System.setProperty("webdriver.chrome.driver", "C:\\Data\\chromedriver.exe");
			webDriver = new ChromeDriver();
			webDriver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			
		}else if(browser.equalsIgnoreCase("FireFox")){
			System.setProperty("webdriver.firefox.driver", "C:\\Data\\gechodriver.exe");
			webDriver = new FirefoxDriver();
		}else
		{
			throw new InvalidParameterException(browser + "is not a valid parameter");
		}
	  	webDriver.manage().window().maximize();
	  	
		return webDriver;
		
	}
	
	public static void quitDriver(WebDriver driver)
	{
		driver.quit();
	}
}
