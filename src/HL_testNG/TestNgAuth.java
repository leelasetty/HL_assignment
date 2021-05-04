package HL_testNG;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

public class TestNgAuth {

	
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
		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(10,TimeUnit.SECONDS);	
		driver.get("https://the-internet.herokuapp.com/login");	
		
		String expStr = driver.getTitle();
		String actualStr = "The Internet";
		System.out.println("skdkfjskdl " + actualStr + "asd s " + expStr);
		Assert.assertEquals(actualStr, expStr);
		//Assert.assert
		System.out.println("same");
	} // end of invokeBrowse

	@AfterMethod
	public void closeURL() {		
		System.out.println(" --- @aftermethod");
		driver.close();
	}
	
	@Test
	public void unameWrong_passwdWrong() throws InterruptedException {
		
		System.out.println("I am here to test at Test  @Test");
		driver.findElement(By.xpath("//input[@id='username']")).sendKeys("wrongadmin");
		Thread.sleep(500);
		driver.findElement(By.xpath("//input[@id='password']")).sendKeys("wrongpasswd");
		Thread.sleep(500);
		driver.findElement(By.xpath("//button[@class='radius']")).click();
		WebElement link = driver.findElement(By.id("flash"));
		String actualStr = link.getText();
		
		/* code to overcome space and * found at the end of actual_string*/
		String modActualString = actualStr.substring(0,(actualStr.length()-2));
		userSoftAssert.assertEquals("Your username is invalid!",modActualString);
		
	}	
	
	@Test
	public void unameCorrect_passwdWrong() throws InterruptedException {
		
		userSoftAssert = new SoftAssert();
		System.out.println("I am here to test at Test  @Test");
		driver.findElement(By.xpath("//input[@id='username']")).sendKeys("tomsmith");
		//Thread.sleep(500
		driver.findElement(By.xpath("//input[@id='password']")).sendKeys("wrongpasswd");
		Thread.sleep(500);
		driver.findElement(By.xpath("//button[@class='radius']")).click();
		WebElement link = driver.findElement(By.id("flash"));
		String actualStr = link.getText();
		
		/* code to overcome space and * found at the end of actual_string*/
		String modActualString = actualStr.substring(0,(actualStr.length()-2));
		userSoftAssert.assertEquals("Your password is invalid!",modActualString);
	}
	
	
	@Test
	public void unameWrong_passwdCorrect() throws InterruptedException {
		
		
		System.out.println("I am here to test at Test  @Test");
		driver.findElement(By.xpath("//input[@id='username']")).sendKeys("wronguser");
		//Thread.sleep(500
		driver.findElement(By.xpath("//input[@id='password']")).sendKeys("SuperSecretPassword!");
		Thread.sleep(500);
		driver.findElement(By.xpath("//button[@class='radius']")).click();
		WebElement link = driver.findElement(By.id("flash"));
		String actualStr = link.getText();
		
		/* code to overcome space and * found at the end of actual_string*/
		String modActualString = actualStr.substring(0,(actualStr.length()-2));
		userSoftAssert.assertEquals("Your username is invalid!",modActualString);
	}
	
	@Test
	public void unameCorrect_passwdCorrect() throws InterruptedException {

		System.out.println("I am here to test at Test  @Test");
		driver.findElement(By.xpath("//input[@id='username']")).sendKeys("tomsmith");
		Thread.sleep(500);
		driver.findElement(By.xpath("//input[@id='password']")).sendKeys("SuperSecretPassword!");
		Thread.sleep(500);
		driver.findElement(By.xpath("//button[@class='radius']")).click();
		WebElement link = driver.findElement(By.id("flash"));
		String actualStr = link.getText();
		
		/* code to overcome space and * found at the end of actual_string*/
		String modActualString = actualStr.substring(0,(actualStr.length()-2));
		userSoftAssert.assertEquals("You logged into a secure area!",modActualString);
	} 
	
		
	@AfterTest
	public void quitURL() {
		System.out.println(" --- @AfterTest ");
		driver.quit();
	}
}  // end_of_TestNgAuth class
