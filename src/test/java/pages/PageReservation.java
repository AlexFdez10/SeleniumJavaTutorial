package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import junit.framework.Assert;

public class PageReservation {

private WebDriver driver;
private By titleTest;
private By passengersDrop;
private By fromPortDrop;
private By toPortDrop;
	
	public PageReservation (WebDriver driver) {
		this.driver= driver;
		titleTest= By.xpath("/html/body/div/table/tbody/tr/td[2]/table/tbody/tr[4]/td/table/tbody/tr/td[2]/table/tbody/tr[3]/td/font/text()");
		passengersDrop= By.name("passCount");
		fromPortDrop= By.name("fromPort");
		toPortDrop= By.name("toPort");
	}
	
	public void assertPageReservation() {
		Assert.assertTrue(driver.findElement(titleTest).getText().contains("Use our Flight Finder"));
	}
	
	public void selectPassengers(int cant) {
		
		WebDriverWait wait = new WebDriverWait(driver, 10);
		WebElement cantidadPasajeros = wait.until(ExpectedConditions.presenceOfElementLocated(passengersDrop));
		Select selectPassengers = new Select(cantidadPasajeros);
		//Select selectPassengers = new Select(driver.findElement(passengersDrop));
		selectPassengers.selectByVisibleText(Integer.toString(cant));
		
	}
	
	public void selectFromPort(int index) {
		Select selectFrom = new Select(driver.findElement(fromPortDrop));
		selectFrom.selectByIndex(3);
	}
	
	public void selectToPort(String ciudad) {
		
		Select selectTo = new Select(driver.findElement(toPortDrop));
		selectTo.selectByValue(ciudad);
		
	}
}
