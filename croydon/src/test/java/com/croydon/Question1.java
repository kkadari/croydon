package com.croydon;

import java.io.IOException;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;


@RunWith(Parameterized.class)
public class Question1 extends TestBase
{
	private String fileName;
	private String regno;
	private String make;
	private String colour;
	
	@Test
	public void checkVehicleInfo() throws IOException
	{
		Boolean testResult;
		
		testResult = homePage
					.startCheck()
					.keyInVehicleReg(regno)
					.isVehicleDetailsMatch(fileName,make,colour,regno,testConfig.getProperty("dataFileFolder")+"\\" + regno + ".jpg");
		
		Assert.assertTrue("Vehicle Details Match",testResult);
				
	}
}
