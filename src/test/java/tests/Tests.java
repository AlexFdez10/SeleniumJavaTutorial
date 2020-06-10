package tests;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import helpers.Helpers;
import helpers.Screenshooter;
import helpers.WebDriverManager;

import org.testng.annotations.BeforeMethod;
import junit.framework.Assert;
import pages.PageLogin;
import pages.PageLogon;
import pages.PageReservation;

public class Tests {
	
	private WebDriver driver;
	ArrayList<String> tabs;
	
	@BeforeMethod
	public void setUp() {
		DesiredCapabilities caps =  new DesiredCapabilities();
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\usuario\\Documents\\chromedriver_win32/chromedriver.exe");
		driver= new ChromeDriver();
		driver.manage().window().maximize();
		//driver.manage().window().setSize(new Dimension(200, 200));
		/* MUEVE LA VENTANA AUTO
		for (int i = 0; i <= 800 ; i++) { driver.manage().window().setPosition(new Point(i, i));}
		*/
		driver.navigate().to("http://newtours.demoaut.com/");
		
		//ABRIR NUEVA TAB EN PARALELO MIENTRAS EJECUTA LOS TEST
		JavascriptExecutor javaScriptExecutor = (JavascriptExecutor)driver;
		String googleWindow = "window.open('http://www.google.com')";
		javaScriptExecutor.executeScript(googleWindow);
		
		tabs= new ArrayList<String>(driver.getWindowHandles());
		
	}
	
	
	@Test
	public void loginIncorrecto() {
		WebDriverManager.setWindowSize(driver, "maximized");
		//CAMBIA LA TAB 2
		driver.switchTo().window(tabs.get(1)).navigate().to("http://www.youtube.com");
		//VUELVO TAB 1 
		driver.switchTo().window(tabs.get(0));
		PageLogin pageLogin = new PageLogin(driver);
		PageLogon pageLogon = new PageLogon(driver);
		pageLogin.login("user", "user");
		
	}
	
	@Test
	public void login() {
		WebDriverManager.setWindowSize(driver, "fullscreen");
		PageLogin pageLogin = new PageLogin(driver);
		PageReservation pagereservation = new PageReservation(driver);
		pageLogin.login("mercury", "mercury");

	}
	
	@Test
	public void pruebaTres() {
		WebDriverManager.setWindowSize(driver, 400,400);
		PageLogin pageLogin = new PageLogin(driver);
		PageReservation pagereservation = new PageReservation(driver);
		pageLogin.login("mercury", "mercury");
		pagereservation.selectPassengers(2);
		pagereservation.selectFromPort(3);
		pagereservation.selectToPort("London");
	}
	
	@Test
	public void pruebaCantidadDeCampos() {
		PageLogin pageLogin = new PageLogin(driver);
		pageLogin.verifyFields();
	}
	
	@AfterMethod
	public void tearDown(ITestResult result) {
		
		if(!result.isSuccess()) {
			Screenshooter.takeScreenshot("Error", driver);
		}
		//CERRAR TAB 2 Y 1
		driver.switchTo().window(tabs.get(1)).close();
		driver.switchTo().window(tabs.get(0)).close();
	}

}
