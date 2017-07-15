package com.croydon;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Properties;

import org.junit.Before;
import org.junit.runners.Parameterized.Parameters;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.junit.After;
import org.openqa.selenium.WebDriver;


public class TestBase 
{
	protected WebDriver webDriver;
	protected HomePage homePage;
	protected Properties testConfig;
	
	@Before
	public void beforeMethod() throws FileNotFoundException, IOException
	{
		testConfig = new Properties();
		testConfig.load(new FileInputStream("config.properties"));

		 webDriver = WebDriverHelper.createDriver(testConfig.getProperty("browser"));
		 System.out.println(testConfig.getProperty("baseUrl"));
		 webDriver.navigate().to(testConfig.getProperty("baseUrl"));

	  	homePage = new HomePage(webDriver);
		
	}
	

	
	@After
	public void afterMethod()
	{

		WebDriverHelper.quitDriver(webDriver);
	}
	

	
	

}
