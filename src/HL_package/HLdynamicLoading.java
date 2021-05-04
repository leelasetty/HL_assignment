package HL_package;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HLdynamicLoading {
	
	WebDriver driver;

public void invokeBrowser() {
		
		System.setProperty("webdriver.chrome.driver","C:\\Users\\Lenovo\\Desktop\\Desktop\\job\\selenium2021\\chromedriver_win32\\chromedriver.exe");
		driver = new ChromeDriver();
		
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(60,TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(50,TimeUnit.SECONDS);	
		
	} // end of invokeBrowse
	
	public void enterURL() {
		driver.get("https://the-internet.herokuapp.com/dynamic_loading");
	}
	
	void exampleTwo() {
		driver.findElement(By.xpath("//a[starts-with(text(),'Example 2: Element')]")).click();
		driver.findElement(By.xpath("//button[text()='Start']")).click();
		WebDriverWait myWait = new WebDriverWait(driver,20);
		
		// Explicit wait till the content appears on the page
		WebElement w = myWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//h4[contains(text(),'Hello World!')]")));
		System.out.println("Text You captured is :" + w.getText() );
			
	} //end_of_exampleTwo
	
	public static void main(String[] args) {
		
		HLdynamicLoading obj = new HLdynamicLoading();
		obj.invokeBrowser();
		obj.enterURL();
		obj.exampleTwo();
		
	}

}
