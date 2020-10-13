package com.memberdues.utility;

import java.io.File;
import java.io.FileInputStream;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelDataProvider {
	XSSFWorkbook wb;
	String Sheetname ="Login";

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
	public String getStringData(int row, int col) {

		return 	wb.getSheet(Sheetname).getRow(row).getCell(col).getStringCellValue();

	}

	public double getNumericData(int row, int col) {

		return 	wb.getSheet(Sheetname).getRow(row).getCell(col).getNumericCellValue();
	}

	// to get test data from a sheet index - method overloading(type has changed, but no of values and method is same)
	//	public String getStringData(int sheetindex, int row, int col) {
	//
	//		return 	wb.getSheetAt(0).getRow(row).getCell(col).getStringCellValue();
	//	}
	//	
	//	public double getNumericData(int sheetindex, int row, int col) {
	//
	//		return 	wb.getSheetAt(0).getRow(row).getCell(col).getNumericCellValue();
	//	}
	//	

	public int GetDataRowNum(String testcasename) {
		int rownum = 0;
		XSSFSheet sh = wb.getSheet(Sheetname);
		int noofRows = sh.getPhysicalNumberOfRows();
		for (int i=1; i <noofRows; i++) {
			String a = sh.getRow(i).getCell(0).getStringCellValue();
			if(a.equals(testcasename)){
				rownum = i;
				break;
			}
			
		}
//		System.out.println("Row number is"+rownum);
		return rownum;
		
	}
	
	public int GetDataColNum(String DataCol) {
		int colnum = 0;
		XSSFSheet sh = wb.getSheet(Sheetname);
		int noofCol = sh.getRow(0).getPhysicalNumberOfCells();
		for (int i=1; i <noofCol; i++) {
			String a = sh.getRow(0).getCell(i).getStringCellValue();
			if(a.equals(DataCol)){
				colnum = i;	
				break;
			}
			
		}
		//System.out.println("Column number is"+colnum);
		return colnum;

	}
	

}