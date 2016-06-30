/*=====================================================================================================
                          LEGAL NOTICE
------------------------------------------------------------------
Company Name: Softtek
Copyright Legend: © 2016 Softtek, Publisher.  All rights reserved.
No part of this publication may be reproduced, stored in a retrieval system, or transmitted in any form or by any means, electronic, 
mechanical, photocopy, recording or otherwise, without the prior written consent of the Publisher 
------------------------------------------------------------------
*/
package com.softtek.automation.functions;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.bean.ColumnPositionMappingStrategy;
import com.opencsv.bean.CsvToBean;


public class CSVFunctions <T>{
	
	T clazz;
	
	public List<String[]> getInfo(String filePath) throws Exception{
		
		return this.getInfo(filePath, ',');
	}
	
	public List<String[]> getInfo(String filePath, char separator) throws Exception{
		
		List<String[]> resultList = null;			
		CSVReader reader = new CSVReader(new FileReader(filePath),separator);
		resultList = reader.readAll();	
		reader.close();
		return resultList;
	}
	
	public List<T> getInfo(String filePath, Class bindClass ,char separator, String[] columns)throws Exception{
		List<T> resultList = null;
		
		CSVReader reader = new CSVReader(new FileReader(filePath),separator);
		ColumnPositionMappingStrategy<T> mapper = new ColumnPositionMappingStrategy <T>();
		mapper.setType(bindClass);		
		if(columns == null || columns.length == 0){
			mapper.setColumnMapping(getColumnsFromClass(bindClass));
		}else{
			mapper.setColumnMapping(columns);
		}		
		CsvToBean<T> csvToBean = new CsvToBean<T>();
		resultList = csvToBean.parse(mapper, reader);		
		return resultList;		
	}

	private String [] getColumnsFromClass(Class clazz){
		
		Field[] fields = clazz.getDeclaredFields();		
		String [] resultArray = new String[fields.length];
		int i = 0;
		for(Field f : fields){			
			f.setAccessible(true);
			resultArray[i] = f.getName();
			i++;
		}
		
		return resultArray;
	}
	
	public static void updateCSV(String fileToUpdate, String replace,
		    int row, int col) throws IOException {

		File inputFile = new File(fileToUpdate);

		// Read existing file 
		CSVReader reader = new CSVReader(new FileReader(inputFile), ',');
		List<String[]> csvBody = reader.readAll();
		// get CSV row column  and replace with by using row and column
		csvBody.get(row)[col] = replace;
		reader.close();

		// Write to CSV file which is open
		CSVWriter writer = new CSVWriter(new FileWriter(inputFile), ',');
		writer.writeAll(csvBody);
		writer.flush();
		writer.close();
	}
	
	public static String obtainCSVValue(String fileToUpdate, int row, int col) throws IOException {

		File inputFile = new File(fileToUpdate);

		// Read existing file 
		CSVReader reader = new CSVReader(new FileReader(inputFile), ',');
		List<String[]> csvBody = reader.readAll();
		// get CSV row column  and replace with by using row and column
		String getValueFromCsv =  csvBody.get(row)[col];
		reader.close();
		return getValueFromCsv;
	}
	
	
}



