package HL_package;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;


public class HLalert {
	WebDriver driver;
	
	public void invokeBrowser() {
		
		System.setProperty("webdriver.chrome.driver","C:\\Users\\Lenovo\\Desktop\\Desktop\\job\\selenium2021\\chromedriver_win32\\chromedriver.exe");
		driver = new ChromeDriver();
		
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(100,TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(50,TimeUnit.SECONDS);	
		
	} // end of invokeBrowse
	
	public void enterURL() {
		driver.get("https://the-internet.herokuapp.com/javascript_alerts");					
	}
	
	public void verify(String eString, String aString ) {
			
		if(eString.equals(aString))
		{
			System.out.println("Message Success with : " + aString);
		}else
		{
			System.out.println("Message Failed with : " + aString);
		}
				
	} //end_of_verify

	public void alertOk() {
		String expectedStr = "You successfully clicked an alert";			
		driver.findElement(By.xpath("//button[text()='Click for JS Alert']")).click();
		Alert aObj = driver.switchTo().alert();
		aObj.accept();
		
		String resultStr = driver.findElement(By.id("result")).getText();
		verify(expectedStr,resultStr);
	}
	

	
	public void alertConfirm(String myStr) {
		
		String expectedStr = "You clicked: Ok";
		//enterURL();	
		driver.findElement(By.xpath("//button[text()='Click for JS Confirm']")).click();
		Alert aObj = driver.switchTo().alert();
		if (myStr.equals("ok")) 
			{
				expectedStr = "You clicked: Ok";
				aObj.accept();
			} 
			else
			{
				expectedStr = "You clicked: Cancel";
				aObj.dismiss();
			}
				
		
		String resultStr = driver.findElement(By.id("result")).getText();
		verify(expectedStr,resultStr);
	}  // end_of_alertConfirm
	
	public void prompt(String myStr) {
		
		String resultStr;
		driver.findElement(By.xpath("//button[text()='Click for JS Prompt']")).click();
		Alert aObj = driver.switchTo().alert();
		aObj.sendKeys(myStr);
		
		if (myStr.equals("OK")) 
			{
				aObj.accept();
				resultStr = driver.findElement(By.id("result")).getText();
				System.out.println("When i am OK           : " + resultStr);
			}
		else
			{
				myStr = "null";
				aObj.dismiss();
				resultStr = driver.findElement(By.id("result")).getText();
				System.out.println("When i have to be null :" + resultStr);
				
			}
		
		
	}// end_of_prompt
	
	
	public static void main(String[] args) {
		
		HLalert obj = new  HLalert();
			obj.invokeBrowser();
			obj.enterURL();
			System.out.println("-----------------Alert Test-----------------");
			obj.alertOk();
			System.out.println("-----------------Confirm Test----------------");
		 	obj.alertConfirm("ok");
			obj.alertConfirm("cancel");
			System.out.println("-----------------Prompt Test----------------");
			obj.prompt("OK");
			obj.prompt("cancel");

	}

}
