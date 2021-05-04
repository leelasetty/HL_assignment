package HL_testNG;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class TestNgAlert {
	
	
	WebDriver driver;
	SoftAssert userSoftAssert;
	
	@BeforeSuite
	public void TestNames() {
		System.out.println("Testing Login Authentication ");
	}
	
	@BeforeClass
	public void startAssert() {
		userSoftAssert = new SoftAssert();
	}

	
	@BeforeMethod
	public void invokeBrowser() {
		
		System.out.println(" --- @Beforemethod ");
		
		System.setProperty("webdriver.chrome.driver","C:\\Users\\Lenovo\\Desktop\\Desktop\\job\\selenium2021\\chromedriver_win32\\chromedriver.exe");
		driver = new ChromeDriver();
		
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(20,TimeUnit.SECONDS);	
		driver.get("https://the-internet.herokuapp.com/javascript_alerts");	
		
		String expStr = driver.getTitle();
		String actualStr = "The Internet";
		Assert.assertEquals(actualStr, expStr);
		
	} // end of invokeBrowse

@AfterMethod
public void closeURL() {		
		System.out.println(" --- @aftermethod");
		driver.close();
	}
	
@Test
public void alertOk() {
		String expectedStr = "You successfully clicked an alert";			
		driver.findElement(By.xpath("//button[text()='Click for JS Alert']")).click();
		Alert aObj = driver.switchTo().alert();
		aObj.accept();
		String resultStr = driver.findElement(By.id("result")).getText();
		
		userSoftAssert.assertEquals(expectedStr,resultStr);
		userSoftAssert.assertAll();
	}
	
@Test	
public void alertConfirmOK() {
		
		String expectedStr = "You clicked: Ok";
		driver.findElement(By.xpath("//button[text()='Click for JS Confirm']")).click();
		Alert aObj = driver.switchTo().alert();
		aObj.accept();			
		String resultStr = driver.findElement(By.id("result")).getText();
		userSoftAssert.assertEquals(expectedStr,resultStr);
		userSoftAssert.assertAll();
		
	}  // end_of_alertConfirmok

@Test	
public void alertConfirmCancel() {
		
		String expectedStr = "You clicked: Cancel";
		driver.findElement(By.xpath("//button[text()='Click for JS Confirm']")).click();
		Alert aObj = driver.switchTo().alert();
		aObj.dismiss();
		String resultStr = driver.findElement(By.id("result")).getText();
		userSoftAssert.assertEquals(expectedStr,resultStr);
		userSoftAssert.assertAll();
	}  // end_of_alertConfirmCancel

@Test
public void promptOK() {
	
	String resultStr;
	driver.findElement(By.xpath("//button[text()='Click for JS Prompt']")).click();
	Alert aObj = driver.switchTo().alert();
	aObj.sendKeys("OK");
	aObj.accept();
	resultStr = driver.findElement(By.id("result")).getText();
	//System.out.println("When i am OK           : " + resultStr);
	userSoftAssert.assertEquals("You entered: OK",resultStr);
	userSoftAssert.assertAll();

}			
			
@Test
public void promptCancel() {
	
	String resultStr;
	driver.findElement(By.xpath("//button[text()='Click for JS Prompt']")).click();
	Alert aObj = driver.switchTo().alert();
	aObj.sendKeys("OK");
	aObj.dismiss();	
	resultStr = driver.findElement(By.id("result")).getText();
	userSoftAssert.assertEquals("You entered: null",resultStr);	
	userSoftAssert.assertAll();
			
}

@AfterTest
public void quitURL() {
	System.out.println(" --- @AfterTest ");
	driver.quit();
}





}
