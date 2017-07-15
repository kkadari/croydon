package com.croydon;

import java.util.Iterator;
import java.util.List;
import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


import org.openqa.selenium.TakesScreenshot;

public class VehicleEnquiryResultsPage extends PageBase{

	public VehicleEnquiryResultsPage(WebDriver driver) {
		super(driver);

	}
	
	public boolean isVehicleDetailsMatch(String fileName, String make, String colour, String regNo,String screenshotName) throws IOException
	{

		boolean makeMatched = false;
		String makeMatch = "Not Matched";
		boolean colourMatched = false;
		String colourMatch = "Not Matched";

		List<WebElement> wlist = driver.findElements(By.className("list-summary-item"));
		System.out.println(wlist.size());
		Iterator<WebElement> itr = wlist.iterator();

		while(itr.hasNext()) {
			String searchStr = itr.next().getText();
		    System.out.println(searchStr);
		    if(searchStr.toUpperCase().contains(make.toUpperCase())){
		    	System.out.println("Make Matches");
		    	makeMatched = true;
		    	makeMatch = "Matched";
		    }
		    if(searchStr.toUpperCase().contains(colour.toUpperCase())){
		    	System.out.println("Colour Matches");
		    	colourMatched = true;
		    	colourMatch = "Matched";
		    }
		}
		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(scrFile, new File(screenshotName));
		
		serviceClass sc = new serviceClass();
		sc.updateDataInFile(makeMatch, colourMatch, regNo, fileName);

		if(makeMatched && colourMatched)
			return true;
		else
			return false;
		
	}

}
