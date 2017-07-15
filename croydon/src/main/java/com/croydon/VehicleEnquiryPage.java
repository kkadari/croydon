package com.croydon;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class VehicleEnquiryPage extends PageBase
{

	public VehicleEnquiryPage(WebDriver driver) {
		super(driver);

	}
	
	public VehicleEnquiryResultsPage keyInVehicleReg(String regno)
	{
		driver.findElement(By.id("Vrm")).sendKeys(regno);
		driver.findElement(By.name("Continue")).click();
		
		return new VehicleEnquiryResultsPage(driver);
	}
	

}
