package mic.sajjad;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Future;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.CookieStore;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.message.BasicNameValuePair;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class BypassLogin {

	public static void main(String[] args) {
		
	
	CookieStore cookieStore = new BasicCookieStore();
	 
	// Create local HTTP context
	HttpClientContext localContext = HttpClientContext.create();
	// Bind custom cookie store to the local context
	
	localContext.setCookieStore(cookieStore);
	 
	HttpGet httpget = new HttpGet("https://www.hepsiburada.com/ayagina-gelsin/giris");
	System.out.println("Executing request " + httpget.getRequestLine());
	 
	httpclient.start();
	 
	// Pass local context as a parameter
	Future<HttpResponse> future = httpclient.execute(httpget, localContext, null);
	
	//--------------------------------------------------------------------
	HttpResponse response1 = future.get();
	System.out.println("Response: " + response1.getStatusLine());
	List<Cookie> cookies = cookieStore.getCookies();
	for (int k = 0; k < cookies.size(); k++) {
	   System.out.println("Local cookie: " + cookies.get(k));
	}
	//--------------------------------------------------------------------
	HttpPost httpPost = new HttpPost("https://www.hepsiburada.com/ayagina-gelsin/Customer/Login");
	List<NameValuePair> params = new ArrayList<NameValuePair>();
	params.add(new BasicNameValuePair("em", "username"));
	params.add(new BasicNameValuePair("p", "password"));
	httpPost.setEntity(new UrlEncodedFormEntity(params));
	Future<HttpResponse> future = httpclient.execute(httpPost,localContext,null);
	HttpResponse response = future.get();
	cookies = cookieStore.getCookies();
	
	System.setProperty("webdriver.chrome.driver","drivers/chromedriver");
	WebDriver driver = new ChromeDriver();
	driver.navigate().to("https://www.hepsiburada.com/siparislerim/");
	 
	org.openqa.selenium.Cookie c;
	for (int i = 0; i < cookies.size(); i++) {
	   System.out.println("Local cookie: " + cookies.get(i));
	   c = new org.openqa.selenium.Cookie(cookies.get(i).getName(),cookies.get(i).getValue());
	   driver.manage().addCookie(c);
	}
	driver.navigate().to("https://www.hepsiburada.com/siparislerim/");
}}
