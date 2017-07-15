package com.croydon;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;


@RunWith(Parameterized.class)
public class Question1 extends TestBase
{
	private String fileName;
	private String regno;
	private String make;
	private String colour;
	//private String makeMatch;
	//private String colourMatch;
	
	public Question1(String fileName, String regno, String make, String colour) {
		super();
		this.fileName = fileName;
		this.regno = regno;
		this.make = make;
		this.colour = colour;
	}
	
	@Parameters
	public static Collection<Object[]> testData() throws InvalidFormatException, IOException
	{	
		ArrayList<ArrayList<String>> rdata = new ArrayList<ArrayList<String>>();
		ArrayList<Object[]> odata = new ArrayList<Object[]>();
		serviceClass dataClass = new serviceClass();
		//dataClass.setFolderName(testConfig.getProperty("dataFileFolder"));
		dataClass.setFolderName("C:\\Data\\DataFiles");
		ArrayList<String> sfiles = new ArrayList<String>();
		sfiles.add("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
		sfiles.add("application/vnd.ms-excel");
		dataClass.setSupportedFileList(sfiles);
		File[] files = dataClass.getFiles();
		for(File f : files){
			ArrayList<ArrayList<String>> tdata = dataClass.readRegNumberFromFile(f.getCanonicalPath());
			for(int i=0;i<tdata.size();i++){
				ArrayList<String> tt = tdata.get(i);
				System.out.println("TT:" + tt.toString());
				Object[] kk = (tt.toArray(new String[tt.size()]));
				//String[] pp = (tt.toArray(new String[tt.size()]));
				//System.out.println("kk:" + kk[1].toString());				
				odata.add(kk);
			}
		}
		
		//odata = (Collection<Object[]>) rdata;
		//System.out.println(odata.toString());
		return odata;
		
	}
	

	@Test
	public void checkVehicleInfo() throws IOException
	{
		Boolean testResult;
		
		testResult = homePage
					.startCheck()
					.keyInVehicleReg(regno)
					.isVehicleDetailsMatch(fileName,make,colour,regno,testConfig.getProperty("testEvidenceFolder")+"\\" + regno + ".jpg");
		
		Assert.assertTrue("Vehicle Details Match",testResult);
				
	}
}
