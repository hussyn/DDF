package mic.sajjad;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import com.bhanu.utility.ResourceHelper;
import com.google.common.io.Files;

public class TestBase {

	public WebDriver driver;
	
	

	public String getScreenShot(String imageName) throws IOException {
		// in case you don't want to supply screen shot name
		if (imageName.equals("")) {
			imageName = "blank";
		}
		File image = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		
		String imagelocation = ResourceHelper.getBaseResourcePath() + "/src/main/java/resources/screenshot/";

		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat formater = new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss");

		String actualImageName = imagelocation + imageName + "_" + formater.format(calendar.getTime()) + ".png";
		
		File destFile = new File(actualImageName);
		
		Files.copy(image, destFile);
		
		return actualImageName;
	}
}
