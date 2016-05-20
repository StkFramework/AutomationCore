package com.softtek.automation.functions;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import com.opencsv.CSVReader;
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
	
}
