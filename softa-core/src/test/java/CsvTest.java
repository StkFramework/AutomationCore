/*=====================================================================================================
                          LEGAL NOTICE
------------------------------------------------------------------
Company Name: Softtek
Copyright Legend: © 2016 Softtek, Publisher.  All rights reserved.
No part of this publication may be reproduced, stored in a retrieval system, or transmitted in any form or by any means, electronic, 
mechanical, photocopy, recording or otherwise, without the prior written consent of the Publisher 
------------------------------------------------------------------
*/

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;
//import org.openqa.selenium.By;
//import org.openqa.selenium.WebDriver;

//import com.jacob.com.LibraryLoader;
//import com.softtek.automation.driver.TestDriver;
import com.softtek.automation.functions.CSVFunctions;

//import autoitx4java.AutoItX;



public class CsvTest {
	private CSVFunctions<CsvTest> csvFunction = new CSVFunctions<CsvTest>();
	
	@Ignore
	public void getAllInformation() throws Exception {
		List<String[]> list = csvFunction.getInfo("C://Test.csv");
		for (String[] strings : list) {
			for (String string : strings) {
				System.out.print(string+" ");
			}
			System.out.println();
		}
		
	}
	
	@Ignore
	public void getAllInformationWithSeparator() throws Exception {
		List<String[]> list = csvFunction.getInfo("C://Test.csv", ',');
		for (String[] strings : list) {
			for (String string : strings) {
				System.out.print(string+" ");
			}
			System.out.println();
		}
		
	}
	
	@Ignore
	public void updateCsv() throws Exception {
		csvFunction.updateCSV("C://Test.csv", "Test", 4, 6);
	}
	
	@Ignore
	public void getSpecificCell() throws Exception {
		String getValue = csvFunction.obtainCSVValue("C://Test.csv", 4, 6);
		System.out.println(getValue);
	}
	
	 public static String jvmBitVersion()
	  {
	    return System.getProperty("sun.arch.data.model");
	  }

	 @Test
	 public void readAutoIT() throws IOException, InterruptedException{
		 final File currDir = new File(".");
			Thread.sleep(1000);
		 String pathProject = currDir.getAbsolutePath();
			Thread.sleep(1000);
			pathProject = pathProject.substring(0, pathProject.length() - 1);
			Thread.sleep(1000);
			String pathExecutable = pathProject + "src\\main\\resources\\executable\\UploadFile.exe";
			pathProject = pathProject + "src\\test\\resources\\com\\brinker\\sa\\automation\\input\\example_contacts-DOB.xlsx";
			System.out.println(pathExecutable + " " + pathProject);
			String newPath = pathExecutable + " " + pathProject;
			Thread.sleep(1000);
			// String[] cmd = {pathProject};
			 String command= newPath;
		 
		 
	//	 String[] cmd = {"C:\\Users\\luis.gonzalezc\\Documents\\Tecnologias y conceptos.xlsx"};
	//	 String command="C:\\parameter3.exe C:\\Users\\luis.gonzalezc\\Documents\\Tecnologias y conceptos.xlsx";
		 Runtime.getRuntime().exec(command);
	//	 Process proc1 = Runtime.getRuntime().exec( "C:\\callOne2.exe",cmd);
	 }
/*	 @Ignore
		public void selectImage2Good() throws InterruptedException {
	    String jacobDllVersionToUse;
	    if (jvmBitVersion().contains("32")){
	    jacobDllVersionToUse = "jacob-1.18-x86.dll";
	    }
	    else {
	    jacobDllVersionToUse = "jacob-1.18-x64.dll";
	    }
	    File file = new File("lib", jacobDllVersionToUse);
	    System.setProperty(LibraryLoader.JACOB_DLL_PATH, file.getAbsolutePath());

	    AutoItX uploadWindow = new AutoItX();
	    
	    String filepath = "C:\\broker session.jpg";
		//	driver.get(filepath);
	 
			Thread.sleep(1000);
	 
		//	driver.findElement(By.id("uploadfile")).click();
	 
			Thread.sleep(1000);
	 
			if (uploadWindow.winWaitActive("Open", "", 5)) {
				if (uploadWindow.winExists("Open")) {
					uploadWindow.sleep(100);
					uploadWindow.send(filepath);
					uploadWindow.controlClick("Open", "", "&Open");
	 
				}
		}
	 }
	 */
	 @Ignore
	 public void UploadImageRunScript() throws IOException{
		 Runtime.getRuntime().exec("C:\\test.exe");
	 }
/*	 
	@Ignore
	public void selectImage() throws InterruptedException {
		
		String workingDir = System.getProperty("C:\\jacob-1.18");
		 
		final String jacobdllarch = System.getProperty("sun.arch.data.model")
		//final String jacobdllarch = System.getProperty("C:\\jacob-1.18")
				.contains("32") ? "jacob-1.18-x86.dll" : "jacob-1.18-x64.dll";
		String jacobdllpath = workingDir + "\\" + jacobdllarch;
		System.out.println(jacobdllarch);
		File filejacob = new File("dll\\jacob-1.18-x64.dll");
		System.out.println(filejacob);
		System.setProperty(LibraryLoader.JACOB_DLL_PATH, //"jacob-1.18-x64.dll");
				filejacob.getAbsolutePath());
		System.out.println(filejacob);
		AutoItX uploadWindow = new AutoItX();
 
	//	driver = new FirefoxDriver();
		String filepath = "C:\broker session.jpg";
	//	driver.get(filepath);
 
		Thread.sleep(1000);
 
	//	driver.findElement(By.id("uploadfile")).click();
 
		Thread.sleep(1000);
 
		if (uploadWindow.winWaitActive("Open", "", 5)) {
			if (uploadWindow.winExists("Open")) {
				uploadWindow.sleep(100);
				uploadWindow.send(filepath);
				uploadWindow.controlClick("Open", "", "&Open");
 
			}
	}

}*/
}
