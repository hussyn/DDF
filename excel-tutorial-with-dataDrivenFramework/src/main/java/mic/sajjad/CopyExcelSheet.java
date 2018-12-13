package mic.sajjad;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class CopyExcelSheet {

	public static void main(String[] args) {
		
		CopyExcelSheet ces = new CopyExcelSheet();
		
		String excelFilePath = "C:/WorkSpace/EmployeeInfo.xlsx";
		
		String copySheetName = "Employee Info";
		
		List<List<String>> selectedRowDataList = ces.getExcelData(excelFilePath, copySheetName, 3, 6);
		
		ces.createExcelSheetWithData(excelFilePath, selectedRowDataList);

	}

	/* Get the data in excel file. 
	 * Return: 2D String list contains specified row data.
	 * excelFilePath :  The exist file path need to copy.
	 * excelSheetName : Which sheet to copy.
	 * startRow : Start row number.
	 * endRow : End row number.
	 * */
	private List<List<String>> getExcelData(String excelFilePath, String excelSheetName, int startRow, int endRow)
	{
		List<List<String>> ret = new ArrayList();
		if(excelFilePath!=null && !"".equals(excelFilePath.trim()) && excelSheetName!=null && !"".equals(excelSheetName.trim()))
		{
			try{
				/* Open the file input stream. */
				FileInputStream fis = new FileInputStream(excelFilePath.trim());
	
				/* Get workbook. */
				Workbook excelWookBook = new XSSFWorkbook(fis);
	
				/* Get sheet by name. */
				Sheet copySheet = excelWookBook.getSheet(excelSheetName);
				
				int fRowNum = copySheet.getFirstRowNum();
				int lRowNum = copySheet.getLastRowNum();
				
				/*  First row is excel file header, so read data from row next to it. */
				for(int i=fRowNum+1; i<lRowNum+1; i++)
				{
					/* Only get desired row data. */
					if(i>=startRow && i<=endRow)
					{
						Row row = copySheet.getRow(i);
						
						int fCellNum = row.getFirstCellNum();
						int lCellNum = row.getLastCellNum();
						
						/* Loop in cells, add each cell value to the list.*/
						List<String> rowDataList = new ArrayList<String>();
						for(int j = fCellNum; j < lCellNum; j++)
						{
							String cValue = row.getCell(j).getStringCellValue();
							rowDataList.add(cValue);
						}
						
						ret.add(rowDataList);
					}
				}
				
			}catch(Exception ex){
				ex.printStackTrace();
			}
		}
		return ret;
	}
	
	
	/* Create a new excel sheet with data. 
	 * excelFilePath :  The exist excel file need to create new sheet.
	 * dataList : Contains all the data that need save to the new sheet.
	 * */
	private void createExcelSheetWithData(String excelFilePath, List<List<String>> dataList)
	{
		if(excelFilePath!=null && !"".equals(excelFilePath.trim()))
		{
			try
			{
				/* Open the exist file input stream. */
				FileInputStream fis = new FileInputStream(excelFilePath.trim());
	
				/* Get workbook. */
				Workbook wookBook = new XSSFWorkbook(fis);
				
				/* Declare the new excel sheet. */
				Sheet newSheet = null;
				
				/* Get workbook sheet iterator. */
				Iterator sheetIterator = wookBook.iterator();
				
				/* If has existing sheet. */
				if(sheetIterator.hasNext())
				{
					newSheet = wookBook.cloneSheet(0);
					
					/* Select this new cloned sheet. */
					newSheet.setSelected(true);
					
					/* Because this is a cloned sheet, should remove all the cloned rows in it. */
					Iterator<Row> rowIterator = newSheet.iterator();
					while(rowIterator.hasNext())
					{
						// Get the clone row.
						Row cloneRow = rowIterator.next();
						
						// Remove the clone row.
						newSheet.removeRow(cloneRow);
						
						/* Because after remove the clone row, the iterator changed, 
						 * so get it again, else java.util.ConcurrentModificationException will be thrown .*/
						rowIterator = newSheet.iterator();
					}
				}else
				{
					newSheet = wookBook.createSheet("New Sheet");
				}
	
				/* Create header row. */
				Row headerRow = newSheet.createRow(0);
				
				headerRow.createCell(0).setCellValue("Name");
				headerRow.createCell(1).setCellValue("Password");
				headerRow.createCell(2).setCellValue("Email");
				headerRow.createCell(3).setCellValue("Age");
				headerRow.createCell(4).setCellValue("Department");
				headerRow.createCell(5).setCellValue("Skill");
				
	
				/* Loop in the row data list, add each row data into the new sheet. */
				if(dataList!=null)
				{
					int size = dataList.size();
					for(int i=0;i<size;i++)
					{
						List<String> cellDataList = dataList.get(i);
						
						/* Create row to save the copied data. */
						Row row = newSheet.createRow(i+1);
						
						int columnNumber = cellDataList.size();
						
						for(int j=0;j<columnNumber;j++)
						{
							String cellValue = cellDataList.get(j);
							row.createCell(j).setCellValue(cellValue);
						}
					}
				}
				
				/* Write the new sheet data to excel file */
				FileOutputStream fOut = new FileOutputStream(excelFilePath);
				wookBook.write(fOut);
				fOut.close();
				
				System.out.println("New sheet added in excel file " + excelFilePath + " successfully. ");
			}catch(Exception ex)
			{
				ex.printStackTrace();
			}
		}
	}

}