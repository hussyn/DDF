package mic.sajjad;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

import com.bhanu.testBase.DataSource;

public class TakeScreenshot {

    @Test

    public void testTakeScreenShot() throws Exception{

		WebDriver driver ;
    	System.setProperty("webdriver.firefox.marionette","C:\\geckodriver.exe");
    	driver = new FirefoxDriver();

        //goto url
    	DataSource dataSource =new DataSource();
    	dataSource.getPageLoadTime();
        driver.get("file:///E:/HTML%20Wireframe/signin.html");

        //Call take screenshot function

        //this.takeSnapShot(driver, "E://TestData//screenshot//test.png") ;     
        takeSnapShot(driver, "E:/TestData/screenshot/test.png") ; 
    }

    /**

     * This function will take screenshot
     * @param webdriver
     * @param fileWithPath
     * @throws Exception
     */

    public static void takeSnapShot(WebDriver webdriver,String fileWithPath) throws Exception{

        //Convert web driver object to TakeScreenshot
    	//You need to type cast WebDriver instance to TakesScreenshot.
//Taking Screenshot Interface                      webDriver instance
        TakesScreenshot scrShot =((TakesScreenshot)webdriver);

        //Call getScreenshotAs method to create image file

                File SrcFile=scrShot.getScreenshotAs(OutputType.FILE);

            //Move image file to new destination

                File DestFile=new File(fileWithPath);

                //Copy file at destination

                FileUtils.copyFile(SrcFile, DestFile);

    }

}