package com.test;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

//@FixMethodOrder(MethodSorters.NAME_ASCENDING)

public class DragAndDrop {

	private WebDriver driver;

	@Before
	public void setUp() throws Exception {

		System.setProperty("webdriver.chrome.driver", "C:\\Pamela\\drivers\\chromedriver.exe");

		driver = new ChromeDriver();

		driver.get("https://jqueryui.com/resources/demos/droppable/default.html");

		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);

		driver.manage().window().maximize();
	}

	@After
	public void tearDown() throws Exception {

		driver.quit();
	}

	@Test 
	public void validaDragAndDrop() throws InterruptedException, IOException {
		
		//System.out.println("teste");
		
		File capturaDeTela = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		
		FileUtils.copyFile(capturaDeTela, new File ("C:\\Pamela\\workspace\\automacao\\src\\test\\resources\\screenAnterior.jpg"));
				
		WebElement origem = driver.findElement(By.id("draggable"));
		
		WebElement destino = driver.findElement(By.id("droppable"));
		
		assertEquals("Drag me to my target", origem.getText());
		
		assertEquals("Drop here", destino.getText());
		
		new Actions(driver).dragAndDrop(origem, destino).perform();
		
		assertEquals("Dropped!", destino.getText());
		
		capturaDeTela = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		
		FileUtils.copyFile(capturaDeTela, new File ("C:\\Pamela\\workspace\\automacao\\src\\test\\resources\\screenPosterior.jpg"));

		Thread.sleep(2000); 
	}
	
}
