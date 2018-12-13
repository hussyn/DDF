package mic.sajjad;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelReading {

	//private static final String Sheet1 = null;

	public static void main(String[] args) throws FileNotFoundException  {
		
		File file=new File("E:/TestData/InputData.xlsx");
		
		FileInputStream fis=new FileInputStream(file);
		//create excelworkBook
		XSSFWorkbook xSSFWorkbook=new XSSFWorkbook();
		//String sheet1 = null;
		XSSFSheet sh = xSSFWorkbook.getSheet("Sheet1");
		System.out.println(sh);
		 double x = sh.getRow(1).getCell(1).getNumericCellValue();
		System.out.println(x);
		//System.out.println(x.getNumericCellValue());

	}

}
