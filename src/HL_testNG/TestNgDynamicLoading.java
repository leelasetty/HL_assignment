package HL_testNG;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class TestNgDynamicLoading {
	
	WebDriver driver;
	SoftAssert userSoftAssert;
	
	@BeforeSuite
	public void TestNames() {
		System.out.println("Testing Dynamic Loading ");
	}
	
	@BeforeClass
	public void startAssert() {
		userSoftAssert = new SoftAssert();
	}

	@BeforeMethod
	public void invokeBrowser() {
			
			System.setProperty("webdriver.chrome.driver","C:\\Users\\Lenovo\\Desktop\\Desktop\\job\\selenium2021\\chromedriver_win32\\chromedriver.exe");
			driver = new ChromeDriver();
			
			driver.manage().window().maximize();
			driver.manage().deleteAllCookies();
			driver.manage().timeouts().implicitlyWait(60,TimeUnit.SECONDS);
			driver.manage().timeouts().pageLoadTimeout(50,TimeUnit.SECONDS);	
			driver.get("https://the-internet.herokuapp.com/dynamic_loading");
			String expStr = driver.getTitle();
			String actualStr = "The Internet";
			Assert.assertEquals(actualStr, expStr);
			
		} // end of invokeBrowse
		
	@Test
	public void waitForText() {
		driver.findElement(By.xpath("//a[starts-with(text(),'Example 2: Element')]")).click();
		driver.findElement(By.xpath("//button[text()='Start']")).click();
		WebDriverWait myWait = new WebDriverWait(driver,20);
		
		// Explicit wait till the content appears on the page
		WebElement w = myWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//h4[contains(text(),'Hello World!')]")));
		System.out.println("Text You captured is :" + w.getText() );
		Assert.assertEquals("Hello World!", w.getText());			
	}
	
	@AfterMethod
	public void closeURL() {		
			System.out.println(" --- @aftermethod");
			driver.close();
		}
	
	@AfterTest
	public void quitURL() {
		System.out.println(" --- @AfterTest ");
		driver.quit();
	}

}
