package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import junit.framework.Assert;

public class PageLogon {

private WebDriver driver;
private By titleTest;
	
	public PageLogon(WebDriver driver) {
		this.driver= driver;
		titleTest = By.xpath("/html/body/div/table/tbody/tr/td[2]/table/tbody/tr[4]/td/table/tbody/tr/td[2]/table/tbody/tr[3]/td/p/font/b");
	}
	
	public void assertLogonPage() {
		Assert.assertTrue(driver.findElement(titleTest).getText().contains("Welcome back to"));
	}
	
}
