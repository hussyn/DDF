package mic.sajjad;

import java.io.File;
import java.io.FileInputStream;
import java.util.Iterator;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

//import com.bhanu.excel.tutorial.ReadDataFromExcelSheet;

public class ReadDataFromExcelSheet {

	public String[][] getExcelData(String excellocation, String sheetName) {
		try {
			String dataSets[][] = null;
			FileInputStream file = new FileInputStream(new File(excellocation));

			// Create Workbook instance holding reference to .xlsx file
			XSSFWorkbook workbook = new XSSFWorkbook(file);

			// Get first/desired sheet from the workbook
			XSSFSheet sheet = workbook.getSheet(sheetName);
			// count number of active rows
			int totalRow = sheet.getLastRowNum();
			// count number of active columns in row
			int totalColumn = sheet.getRow(0).getLastCellNum();
			// Create array of rows and column
			dataSets = new String[totalRow][totalColumn];
			// Iterate through each rows one by one
			Iterator<Row> rowIterator = sheet.iterator();
			int i = 0;
			while (rowIterator.hasNext()) {
				//System.out.println(i);

				Row row = rowIterator.next();
				// For each row, iterate through all the columns
				Iterator<Cell> cellIterator = row.cellIterator();
				int j = 0;
				while (cellIterator.hasNext()) {

					Cell cell = cellIterator.next();
					if (cell.getStringCellValue().contains("User Name")) {
						break;
					}

					// Check the cell type and format accordingly
					switch (cell.getCellType()) {
					case Cell.CELL_TYPE_NUMERIC:
						dataSets[i - 1][j++] = cell.getStringCellValue();
						System.out.println(cell.getNumericCellValue());
						break;
					case Cell.CELL_TYPE_STRING:
						dataSets[i - 1][j++] = cell.getStringCellValue();
						System.out.println(cell.getStringCellValue());
						break;
					case Cell.CELL_TYPE_BOOLEAN:
						dataSets[i - 1][j++] = cell.getStringCellValue();
						System.out.println(cell.getStringCellValue());
						break;
					case Cell.CELL_TYPE_FORMULA:
						dataSets[i - 1][j++] = cell.getStringCellValue();
						System.out.println(cell.getStringCellValue());
						break;
					}

				}

				System.out.println("");
				i++;
			}
			file.close();
			return dataSets;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}	
	
		
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String excellocation ="E:/TestData/demo.xlsx";
		//String sheetName = "loginData";
		String sheetName = "TestData";
		ReadDataFromExcelSheet excel = new ReadDataFromExcelSheet();
		String[][] data = excel.getExcelData(excellocation, sheetName);
		System.out.println(data);
	}

}
