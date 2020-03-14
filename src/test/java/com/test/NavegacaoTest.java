package com.test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

//@FixMethodOrder(MethodSorters.NAME_ASCENDING)

public class NavegacaoTest {

	private WebDriver driver;

	@Before
	public void setUp() throws Exception {

		System.setProperty("webdriver.chrome.driver", "C:\\Pamela\\drivers\\chromedriver.exe");

		driver = new ChromeDriver();

		driver.get("http://antoniotrindade.com.br/treinoautomacao/index.html");

		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);

		driver.manage().window().maximize();
	}

	@After
	public void tearDown() throws Exception {

		driver.quit();
	}

	@Test //@Ignore("Colocar id da task")
	public void navegaAbas() throws InterruptedException {

		assertEquals("Treino Automação de Testes", driver.getTitle());

		WebElement linkDrag = driver.findElement(By.linkText("Drag and Drop"));
		linkDrag.click();

		ArrayList<String> abas = new ArrayList<String>(driver.getWindowHandles());

		driver.switchTo().window(abas.get(1));

		//Thread.sleep(2000);

		assertEquals("Mootools Drag and Drop Example", driver.getTitle());

		driver.switchTo().window(abas.get(0));
		//Thread.sleep(2000);

		assertEquals("Treino Automação de Testes", driver.getTitle());
	}

	@Test
	public void navegaPagina() throws InterruptedException {

		assertEquals("Treino Automação de Testes", driver.getTitle());

		WebElement linkLocalizar = driver.findElement(By.linkText("Localizar Table"));
		linkLocalizar.click();
		
		assertEquals("Trabalhando com tables", driver.getTitle());
		Thread.sleep(2000);
		
		WebElement linkCalculadora = driver.findElement(By.linkText("Calculadora"));
		linkCalculadora.click();
		
		assertEquals("Desafio Automação Cálculos", driver.getTitle());
		Thread.sleep(2000);
		
		driver.navigate().back();
		
		assertEquals("Trabalhando com tables", driver.getTitle());
		
		driver.navigate().back();
		
		assertEquals("Treino Automação de Testes", driver.getTitle());
		
		driver.navigate().forward();
		
		assertEquals("Trabalhando com tables", driver.getTitle());
				
/*
		Thread.sleep(2000);

		assertEquals("Mootools Drag and Drop Example", driver.getTitle());

		Thread.sleep(2000);

		assertEquals("Treino Automação de Testes", driver.getTitle());
		*/
	}

}
