package HL_package;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;


public class HLauthentication {

	WebDriver driver;
	
	public void invokeBrowser() {
		
		System.setProperty("webdriver.chrome.driver","C:\\Users\\Lenovo\\Desktop\\Desktop\\job\\selenium2021\\chromedriver_win32\\chromedriver.exe");
		driver = new ChromeDriver();
		
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(10,TimeUnit.SECONDS);			
	} // end of invokeBrowse
	
	
	public void enterURL() {
		driver.get("https://the-internet.herokuapp.com/login");					
	}
	
	public void quitURL() {
		driver.quit();
	}
	
	public void verify(String eString, String aString) {
		
		if (eString.equals(aString))
		{			
			System.out.println("Login Success :" +  aString);
		}
		else {
			System.out.println("Login fail    : " +  aString);
		}
	} // end of verify
	
	
	public void unameWr_passwdWr(String uname, String passwd, String ExpectedStr) throws InterruptedException  {
		
		enterURL();		
		driver.findElement(By.xpath("//input[@id='username']")).sendKeys(uname);
		Thread.sleep(500);
		driver.findElement(By.xpath("//input[@id='password']")).sendKeys(passwd);
		Thread.sleep(500);
		driver.findElement(By.xpath("//button[@class='radius']")).click();
		WebElement link = driver.findElement(By.id("flash"));
		String actualStr = link.getText();
		
		//System.out.println("actual string : " + actualStr + "--" + actualStr.length());
		
		/* code to overcome space and * found at the end of actual_string*/
		String modActualString = actualStr.substring(0,(actualStr.length()-2));
		verify(ExpectedStr,modActualString);
						
	} //end_of_unameWr_passwdW
	
	//Main Starts Here 		
	public static void main(String[] args) throws InterruptedException {
		String loginSuccess = "You logged into a secure area!";
		String unameInval = "Your username is invalid!";
		String passwordInval = "Your password is invalid!";
		
		HLauthentication obj = new  HLauthentication();
		obj.invokeBrowser();
		obj.unameWr_passwdWr("wrongadmin","wrongadmin",unameInval);
		obj.unameWr_passwdWr("tomsmith","wrongadmin",passwordInval);
		obj.unameWr_passwdWr("wrongadmin","SuperSecretPassword!",unameInval);
		obj.unameWr_passwdWr("tomsmith","SuperSecretPassword!",loginSuccess);
		obj.quitURL();
		
	}

}



