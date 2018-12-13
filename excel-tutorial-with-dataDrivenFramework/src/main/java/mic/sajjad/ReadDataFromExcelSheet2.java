package mic.sajjad;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * @author Bhanu Pratap https://www.youtube.com/user/MrBhanupratap29/playlists
 */
public class ReadDataFromExcelSheet2 {

	
	public void updateResult(String excellocation, String sheetName, String testCaseName, String testStatus) throws IOException {

		try {
			FileInputStream file = new FileInputStream(new File(excellocation));

			// Create Workbook instance holding reference to .xlsx file
			XSSFWorkbook workbook = new XSSFWorkbook(file);

			// Get first/desired sheet from the workbook
			XSSFSheet sheet = workbook.getSheet(sheetName);
			// count number of active tows
			int totalRow = sheet.getLastRowNum() + 1;
			// count number of active columns in row
			for (int i = 1; i < totalRow; i++) {
				XSSFRow r = sheet.getRow(i);
				String ce = r.getCell(1).getStringCellValue();
				if (ce.contains(testCaseName)) {
					r.createCell(2).setCellValue(testStatus);
					file.close();
					System.out.println("resule updated");
					FileOutputStream outFile = new FileOutputStream(new File(excellocation));
					workbook.write(outFile);
					outFile.close();
					break;
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}



	public static void main(String[] args) throws IOException {
		//String excellocation = "E:/TestData/demo.xlsx";
		String excellocation ="E:/TestData/demo.xlsx";
		String sheetName = "TestReport";
		//String sheetName = "loginData";
		ReadDataFromExcelSheet2 excel = new ReadDataFromExcelSheet2();
		//Object[][] data = excel.getExcelDataBasedOnStartingPoint(excellocation, sheetName, "loginData");
		//Object[][] data = excel.getExcelDataBasedOnStartingPoint(excellocation, sheetName, "login");
		//System.out.println(data);
		 excel.updateResult(excellocation, sheetName, "Login Test", "FAIL");
		 excel.updateResult(excellocation, sheetName, "Registartion Test","PASS");
		 excel.updateResult(excellocation, sheetName, "Dashboard Test", "PASS");
	}
}
