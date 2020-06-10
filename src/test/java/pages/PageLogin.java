package pages;

import java.io.File;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import helpers.Helpers;
import junit.framework.Assert;

public class PageLogin {

	private WebDriver driver;
	private By userField; 
	private By passField;
	private By loginBotton;
	private By fields;
	
	public PageLogin(WebDriver driver) {
		this.driver= driver;
		userField= By.name("userName");
		passField = By.name("password");
		loginBotton = By.name("login");
		fields = By.tagName("input");
	}
	
	
	public void login(String user, String pass) {
		driver.findElement(userField).sendKeys(user);
		driver.findElement(passField).sendKeys(pass);
		driver.findElement(loginBotton).click();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		File myScreenShot =((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		try {
			FileUtils.copyFile(myScreenShot, new File("LOGIN "+System.currentTimeMillis()+".png"));
		}catch (Exception e) {
			e.printStackTrace();
		}
		//Helpers helpers = new Helpers();
		//helpers.espera(4);
		
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

	}
	
	public void fields_login (String user, String pass) {
		
		List<WebElement> loginFields = driver.findElements(fields);
		loginFields.get(1).sendKeys(user);
		loginFields.get(2).sendKeys(pass);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
	}
	
	public void verifyFields() {
		
		List<WebElement> loginFields = driver.findElements(fields);
		System.out.println(loginFields.size());
		Assert.assertTrue(loginFields.size()==5);
		
	}
	
}
