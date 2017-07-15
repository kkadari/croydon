package com.croydon;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
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
	protected static Properties testConfig;
	
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

		//WebDriverHelper.quitDriver(webDriver);
	}
	
	@Parameters
	public static Collection<Object[]> testData() throws InvalidFormatException, IOException
	{	
		ArrayList<ArrayList<String>> rdata = new ArrayList<ArrayList<String>>();
		ArrayList<Object[]> odata = new ArrayList<Object[]>();
		serviceClass dataClass = new serviceClass();
		dataClass.setFolderName(testConfig.getProperty("dataFileFolder"));
		
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
	
	

}
