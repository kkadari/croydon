package com.croydon;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HomePage extends PageBase 
{
	protected Properties testConfig;
	
	public HomePage(WebDriver driver) throws FileNotFoundException, IOException
	{
		super(driver);
		testConfig = new Properties();
		testConfig.load(new FileInputStream("config.properties"));
	}
	
	public VehicleEnquiryPage startCheck()
	{
		driver.findElement(By.xpath("//*[@id=\"get-started\"]/a")).click();

		return new VehicleEnquiryPage(driver);
		
	}
	

	
}
