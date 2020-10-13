//package com.memberdues.utility;
//
//import java.io.File;
//import java.io.FileInputStream;
//
//import org.apache.poi.xssf.usermodel.XSSFSheet;
//import org.apache.poi.xssf.usermodel.XSSFWorkbook;
//
//public class TestDataExtract {
//
//
//
//	public static void main(String[] args) {
//		XSSFWorkbook wb = null ;
//		File src = new File("./TestData/Data.xlsx");
//		try {
//			FileInputStream fis = new FileInputStream(src);
//			wb = new XSSFWorkbook(fis);
//		} catch (Exception e) {
//			System.out.println("Unable to Read Test Data File" + e.getMessage());
//		}
//		int at = 0, bt =0;
//		XSSFSheet sh = wb.getSheet("Login");
//		int noofRows = sh.getPhysicalNumberOfRows();
//		for (int i=1; i <noofRows; i++) {
//			String a = wb.getSheet("Login").getRow(i).getCell(0).getStringCellValue();
//			if(a.equals("First_Time_Reset_Password")){
//				System.out.println("Row Number is "+i);
//				i=at;
//				break;
//			}
//			
//		}
//		System.out.println("Value of At "+at);
//		int noofCol = sh.getRow(0).getPhysicalNumberOfCells();
//		System.out.println("No of Cols "+noofCol);
//		for (int j=1; j <noofCol; j++) {
//			String b = sh.getRow(0).getCell(j).getStringCellValue();
//			if(b.equals("Password")){
//				System.out.println("Col number is "+j);
//				j=bt;
//				System.out.println("Row Value "+at);
//				String val = wb.getSheet("Login").getRow(at).getCell(bt).getStringCellValue();
//				System.out.println(val);
//				break;
//			}
//
//		}
//		
//
//	}
//
//
//}
//
