package com.memberdues.utility;

import java.io.File;
import java.io.FileInputStream;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelDataProvider {
	XSSFWorkbook wb;

	public ExcelDataProvider() {

		File src = new File("./TestData/Data.xlsx");

		try {
			FileInputStream fis = new FileInputStream(src);
			wb = new XSSFWorkbook(fis);
		} catch (Exception e) {
			System.out.println("Unable to Read Test Data File" + e.getMessage());
		}
	}

// to get test data from a particular sheet 
	public String getStringData(String Sheetname, int row, int col) {

		return 	wb.getSheet(Sheetname).getRow(row).getCell(col).getStringCellValue();

	}

	public double getNumericData(String Sheetname, int row, int col) {

		return 	wb.getSheet(Sheetname).getRow(row).getCell(col).getNumericCellValue();
	}

// to get test data from a sheet index - method overloading(type has changed, but no of values and method is same)
	public String getStringData(int sheetindex, int row, int col) {

		return 	wb.getSheetAt(sheetindex).getRow(row).getCell(col).getStringCellValue();
	}
	
	public double getNumericData(int sheetindex, int row, int col) {

		return 	wb.getSheetAt(sheetindex).getRow(row).getCell(col).getNumericCellValue();
	}


}
