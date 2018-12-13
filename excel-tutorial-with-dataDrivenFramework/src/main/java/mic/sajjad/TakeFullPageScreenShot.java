package mic.sajjad;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.ScreenshotException;

import com.thoughtworks.selenium.ScreenshotListener;

public class TakeFullPageScreenShot
{
public static void main(String args[]) throws Exception
{
System.setProperty("webdriver.chrome.driver" , "C:\\chromedriver.exe");
WebDriver driver = new ChromeDriver();
driver.get("https://www.lambdatest.com/");


}
}