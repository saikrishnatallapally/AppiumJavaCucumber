package com.challengeapp.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtility {

	public static Map<String, String> testData = new HashMap<String, String>();

	public static void excelSheetReading(String excelSheetFileName, String sheetName) throws IOException {
		// for testData (fetching all the sheets' data)
		String projectDir = System.getProperty("user.dir");

		FileInputStream fileInputStream = new FileInputStream(projectDir + "\\Excel\\" + excelSheetFileName + ".xlsx");
		System.out.println(fileInputStream);
		XSSFWorkbook workbook = null;
		workbook = new XSSFWorkbook(fileInputStream);

		XSSFSheet sheet = workbook.getSheet(sheetName);
		for (int rowCount = 0; rowCount < sheet.getPhysicalNumberOfRows(); rowCount++) {
			Cell cell = sheet.getRow(rowCount).getCell(1);
			
				testData.put(sheet.getRow(rowCount).getCell(0).getStringCellValue(),
						sheet.getRow(rowCount).getCell(1).getStringCellValue());
		}
	}
}
