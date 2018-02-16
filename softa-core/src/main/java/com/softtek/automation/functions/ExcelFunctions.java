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
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.util.CellReference;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;

@Component
public class ExcelFunctions {
	
	/**
	 * Method that return a workbook from specific file.
	 * @param fileName
	 * @return XSSFWorkbook
	 * @throws IOException
	 */
	public XSSFWorkbook getWoorkbookFromFile(final String fileName) throws IOException {
		final FileInputStream file = new FileInputStream(new File(fileName));
		final XSSFWorkbook workbook = new XSSFWorkbook(file);
		return workbook;
	}

	/**
	 * Method that return a sheet from specific file.
	 * @param fileName
	 * @return XSSFWorkbook
	 * @throws IOException
	 */
	public XSSFSheet getSheetFromFile(final String fileName, final String commonSheetName) throws IOException {
		final XSSFWorkbook workbook = this.getWoorkbookFromFile(fileName);

		final XSSFSheet sheet = workbook.getSheet(commonSheetName);
		return sheet;
	}

	/**
	 * Method to obtain a the value of specific cell
	 * @param sheet
	 * @param cell
	 * @return String
	 */
	public String obtainCellValue(final Sheet sheet, final String cell) {
		String cellValue = "";
		final CellReference ref = new CellReference(cell);
		final Row r = sheet.getRow(ref.getRow());
		if (r != null) {
			final Cell c = r.getCell(ref.getCol());
			if (c.getCellType() == 2) {
				cellValue =
						""
								+ sheet.getWorkbook().getCreationHelper().createFormulaEvaluator().evaluate(c)
										.getNumberValue();
			}
			else {
				cellValue = c.toString();
			}
		}
		return cellValue;
	}
	
	/**
	 * Method to obtain a the value of specific cell
	 * @param sheet
	 * @param cell
	 * @return String
	 * @throws IOException 
	 */
	public String obtainCellValue(final String fileName, final String commonSheetName, final String cell) throws IOException {
		final XSSFWorkbook workbook = this.getWoorkbookFromFile(fileName);

		final XSSFSheet sheet = workbook.getSheet(commonSheetName);
		String cellValue = "";
		final CellReference ref = new CellReference(cell);
		final Row r = sheet.getRow(ref.getRow());
		if (r != null) {
			final Cell c = r.getCell(ref.getCol());
			if (c.getCellType() == 2) {
				cellValue =
						""
								+ sheet.getWorkbook().getCreationHelper().createFormulaEvaluator().evaluate(c)
										.getNumberValue();
			}
			else {
				cellValue = c.toString();
			}
		}
		return cellValue;
	}

	/**
	 * Method to obtain comment of specific cell
	 * @param sheet
	 * @param cell
	 * @return String
	 */
	public String obtainCellComment(final Sheet sheet, final String cell) {
		String cellComment = "";
		final CellReference ref = new CellReference(cell);
		final Row r = sheet.getRow(ref.getRow());
		if (r != null) {
			final Cell c = r.getCell(ref.getCol());
			cellComment = c.getCellComment().getString().toString();
		}
		return cellComment;
	}

	/**
	 * Method to obtain all the Items in a row.
	 * @param sheet
	 * @param rowNumber
	 * @return Map<String, ArrayList<String>>
	 */
	public Map<String, ArrayList<String>> obtainRowItems(final Sheet sheet, final int rowNumber) {

		final Map<String, ArrayList<String>> hashmap = new HashMap<String, ArrayList<String>>();
		final int colNum = sheet.getRow(rowNumber).getPhysicalNumberOfCells();
		final Row row = sheet.getRow(rowNumber);

		ArrayList<String> arraylist = null;

		if (!ExcelFunctions.isEmptyRow(row)) {
			for (int j = 0; j < colNum; j++) {
				arraylist = new ArrayList<String>();
				final Cell cell = row.getCell(j);
				if (!(cell == null)) {
					final String cellValue = obtainCellValue(sheet, ((XSSFCell) cell).getReference().toString());
					String comment = "";
					try {
						comment = obtainCellComment(sheet, ((XSSFCell) cell).getReference().toString());
					}
					catch (final NullPointerException nullPointerException) {
					//	ExcelCommons.LOGGER.error("There is null pointer exception for " + cellValue);
					}
					arraylist.add(cellValue);
					arraylist.add(comment);
					hashmap.put(((XSSFCell) cell).getReference(), arraylist);
				}
			}
		}
		return hashmap;

	}

	/**
	 * Used to compare files using names
	 * @param file1
	 * @param file2
	 * @param commonSheetName
	 * @param initialRow 
	 * @return boolean
	 * @throws IOException
	 * @since Aug 28, 2014
	 */
	public boolean compareFiles(final String file1, final String file2, final String commonSheetName,
			final int initialRow) throws IOException {

		final XSSFWorkbook workbook1 = this.getWoorkbookFromFile(file1);
		final XSSFWorkbook workbook2 = this.getWoorkbookFromFile(file2);

		final XSSFSheet ws1 = workbook1.getSheet(commonSheetName);
		final XSSFSheet ws2 = workbook2.getSheet(commonSheetName);

		return this.compareFiles(ws1, ws2, initialRow);
	}

	/**
	 * Method that verify if two files are the same using sheets
	 * @param file1
	 * @param file2
	 * @param initialRow 
	 * @return boolean
	 */
	public boolean compareFiles(final Sheet file1, final Sheet file2, final int initialRow) {
		final int rowNum = file1.getLastRowNum() + 1;
		int index = 0;
		for (int rowCount = initialRow; rowCount < rowNum; rowCount++) {
			final XSSFRow row = (XSSFRow) file1.getRow(rowCount);
			if (!ExcelFunctions.isEmptyRow(row)) {
				final Map<String, ArrayList<String>> resultFile1 = obtainRowItems(file1, rowCount);
				final Map<String, ArrayList<String>> resultFile2 = obtainRowItems(file2, rowCount);

				final Set<Entry<String, ArrayList<String>>> entryResult1 = resultFile1.entrySet();

				for (final Map.Entry<String, ArrayList<String>> entry : entryResult1) {
					final String keyValue = entry.getKey().toString();

					if (!resultFile1.get(keyValue).equals(resultFile2.get(keyValue))) {
						return false;
					}
				}

				index++;

			}
		}
		return true;
	}

	/***
	 * This method return true if a row is empty
	 * @param row
	 * @return boolean
	 */
	public static boolean isEmptyRow(final Row row) {
		boolean isEmptyRow = true;
		for (int cellNum = row.getFirstCellNum(); cellNum < row.getLastCellNum(); cellNum++) {
			final Cell cell = row.getCell(cellNum);
			if (cell != null && cell.getCellType() != Cell.CELL_TYPE_BLANK && StringUtils.isNotBlank(cell.toString())) {
				isEmptyRow = false;
			}
		}
		return isEmptyRow;
	}
	
	public void writeCellData(final String file, final String commonSheetName, final String data, final String reference) throws IOException{
		final FileInputStream fis = new FileInputStream(new File(file));
		final XSSFWorkbook workbook = new XSSFWorkbook(fis);
		final XSSFSheet sheet = workbook.getSheet(commonSheetName);

		final CellReference cellReference = new CellReference(reference);
		final Row row = sheet.getRow(cellReference.getRow());
		final Cell cell = row.getCell(cellReference.getCol());

		final String cellValue = data;
		if (this.isNumeric(cellValue)) {
		cell.setCellValue(Double.parseDouble(cellValue));
		}
		else {
		cell.setCellValue(cellValue);
		}

		fis.close();
		final FileOutputStream fos = new FileOutputStream(new File(file));
		workbook.write(fos);
		fos.close();
	}
	
	/**
	 * Test if the input string is a numeric value and can be converted to Long.
	 * @param value
	 * @return boolean
	 * */
	public static boolean isNumeric(final String value) {
		try {
			Double.parseDouble(value);
		}
		catch (final Exception e) {
			return false;
		}
		return true;
	}

	public int getRow(final String file, final String commonSheetName) throws IOException {
		final FileInputStream fis = new FileInputStream(new File(file));
		final XSSFWorkbook workbook = new XSSFWorkbook(fis);
		final XSSFSheet sheet = workbook.getSheet(commonSheetName);
		final int rowNum = sheet.getLastRowNum() + 1;
		return rowNum;
	}

	public int getLastRow(final String file1, final String commonSheetName) throws IOException{
		final XSSFWorkbook workbook1 = this.getWoorkbookFromFile(file1);
		final XSSFSheet ws = workbook1.getSheet(commonSheetName);
		return ws.getLastRowNum() + 1;
	}
	
	public void writeExcel(List<Users> listBook, String excelFilePath, String commonSheetName) throws IOException {
		final XSSFWorkbook workbook = this.getWoorkbookFromFile(excelFilePath);
		final XSSFSheet ws1 = workbook.getSheet(commonSheetName);
	//    Workbook workbook = new HSSFWorkbook();
	//    Sheet sheet = workbook.createSheet();
	    
	 
	    int rowCount = ws1.getLastRowNum()+1;
	    
	    Row row = ws1.createRow(rowCount);
	    
	 //   XSSFRow row = (XSSFRow) ws1.getRow(rowCount);
	    System.out.println("Row:"+rowCount);
	 
	    for (Users aBook : listBook) {
	        writeBook(aBook, row);
	    //    row = ws1.createRow(++rowCount);
	    }
	 
	    try (FileOutputStream outputStream = new FileOutputStream(excelFilePath)) {
	        workbook.write(outputStream);
	    }
	}
	
	private void writeBook(Users aBook, Row row) {
		
	    Cell cell = row.createCell(0);
	    cell.setCellValue(aBook.getName1stLevel());
	 
	    cell = row.createCell(1);
	    cell.setCellValue(aBook.getName2ndLevel());
	    
	    cell = row.createCell(2);
	    cell.setCellValue(aBook.getRole());
	 
	    cell = row.createCell(3);
	    cell.setCellValue(aBook.getLocation());
	    
	    cell = row.createCell(4);
	    cell.setCellValue(aBook.getArea());
	    
	    cell = row.createCell(5);
	    cell.setCellValue(aBook.getPhone());
	 
	    cell = row.createCell(6);
	    cell.setCellValue(aBook.getExt());
	    
	    cell = row.createCell(7);
	    cell.setCellValue(aBook.getEmail());
	}

}
