package com.croydon;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.commons.io.FilenameUtils;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.ss.util.WorkbookUtil;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class serviceClass implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String folderName;
	private String outputFolder;
	private ArrayList<String> supportedFileList;
	
	public serviceClass() { }
	
	public String getOutputFolder() {
		return outputFolder;
	}

	public void setOutputFolder(String outputFolder) {
		this.outputFolder = outputFolder;
	}

	public String getFolderName() {
		return folderName;
	}

	public void setFolderName(String folderName) {
		this.folderName = folderName;
	}

	public ArrayList<String> getSupportedFileList() {
		return supportedFileList;
	}

	public void setSupportedFileList(ArrayList<String> supportedFileList) {
		this.supportedFileList = supportedFileList;
	}

	public void listFiles() throws IOException
	{
		File f = new File(folderName);
		File[] files = f.listFiles();
		for(int i=0;i<files.length;i++)
		{
			if(files[i].isFile()){
				System.out.println("File name:" + files[i].getName());
				System.out.println("File length in KB:" + files[i].length()/1024);
				System.out.println("File type:" + Files.probeContentType(files[i].toPath()));
				System.out.println("File extension:" + FilenameUtils.getExtension(files[i].getName()));
			}
		}	
	}
	
	public File[] getFiles() throws IOException
	{
		String mime_type;
		ArrayList<File> af = new ArrayList<File>();
		File f = new File(folderName);
		File[] files = f.listFiles();
		for(int i=0;i<files.length;i++)
		{
			if(files[i].isFile()){
				mime_type = Files.probeContentType(files[i].toPath());
				if(supportedFileList.toString().contains(mime_type)){
					af.add(files[i]);
					System.out.println("File added to support list:" + files[i].getName());
				}
				System.out.println("File type:" + Files.probeContentType(files[i].toPath()));
				

			}
		}	
		return af.toArray(new File[af.size()]);
	}
	
	private ArrayList<ArrayList<String>> readRegNumberFromExcelFile(String fileName) throws InvalidFormatException, IOException
	{
		FileInputStream fis = new FileInputStream(fileName);
		// Create a Apache POI workbook object for the excel file path provided
		Workbook workbook = WorkbookFactory.create(fis);
		Row row = null;
		
		ArrayList<ArrayList<String>> rdata = new ArrayList<ArrayList<String>>();
		int rowCount = workbook.getSheetAt(0).getPhysicalNumberOfRows();
		for(int i=1;i<rowCount;i++)
		{
			row = workbook.getSheetAt(0).getRow(i);
			Iterator<Cell> it = row.iterator();
			ArrayList<String> rowdata = new ArrayList<String>();
			rowdata.add(fileName);
			while(it.hasNext())
			{
				rowdata.add(it.next().toString());
			}
			rdata.add(rowdata);
		}
		System.out.println("Rows: " + rdata.toString());
		return rdata;
		
	}
	
	private ArrayList<ArrayList<String>> readRegNumberFromCSVFile(String fileName) throws IOException
	{
		ArrayList<ArrayList<String>> rdata = new ArrayList<ArrayList<String>>();
		BufferedReader br = new BufferedReader(new FileReader(fileName));

		    String line = br.readLine();
		    line = br.readLine();
		    //System.out.println("Line:" + line);
		    while (line != null) {
		        
		        System.out.println("Line:" + line);
			    String[] tokens = line.split(",");
				ArrayList<String> rowdata = new ArrayList<String>();
				rowdata.add(fileName);
			    for(int i=0;i<tokens.length;i++){
			    	rowdata.add(tokens[i]);
			    }
		        rdata.add(rowdata);
		        line = br.readLine();
		    }

		br.close();
		System.out.println("Rows: " + rdata.toString());
		return rdata;
		
	}
	
	
	public ArrayList<ArrayList<String>>  readRegNumberFromFile(String fileName) throws InvalidFormatException, IOException
	{
		String fextn = FilenameUtils.getExtension(fileName);
		ArrayList<ArrayList<String>>  rdata = null;
		switch(fextn)
		{
			case "xlsx":
			case "xls": rdata = readRegNumberFromExcelFile(fileName);
						break;
			case "csv": rdata = readRegNumberFromCSVFile(fileName);
						break;
		}
		return rdata;
	}
	
	public void updateDataInFile(String make, String colour, String regNo, String fileName) throws IOException
	{
		BufferedWriter bw = new BufferedWriter(new FileWriter("Output_File.csv",true));		
		StringBuffer bf = new StringBuffer();
		bf.append(fileName).append(",").append(regNo).append(",").append(make)
			.append(",").append(colour);
		bw.write(bf.toString());
		bw.newLine();
		bw.close();

	}
	
	

}
