package com.test;

import static org.junit.Assert.*;

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

//@FixMethodOrder(MethodSorters.NAME_ASCENDING)

public class ValidaRegex {

	private WebDriver driver;

	@Before
	public void setUp() throws Exception {

		System.setProperty("webdriver.chrome.driver", "C:\\Pamela\\drivers\\chromedriver.exe");

		driver = new ChromeDriver();

		driver.get("https://www.geradordecpf.org/");

		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);

		driver.manage().window().maximize();
	}

	@After
	public void tearDown() throws Exception {

		driver.quit();
	}

	@Test 
	public void validaMascaradeCPF() throws InterruptedException {
		
		//System.out.println("teste");
		
		String cpf;

		WebElement btnGerar = driver.findElement(By.id("btn-gerar-cpf"));
		
		btnGerar.click();
		
		cpf = driver.findElement(By.name("numero")).getAttribute("value");

		assertTrue(cpf.matches("^[0-9]{11}$"));

		Thread.sleep(2000); 
	}
	

	@Test 
	public void validaCPFSemMascara() throws InterruptedException {
		
		//System.out.println("teste");
		
		WebElement check = driver.findElement(By.id("cbPontos"));
		
		check.click();
				
		WebElement btnGerar = driver.findElement(By.id("btn-gerar-cpf"));
		
		btnGerar.click();
		
		String cpf = driver.findElement(By.name("numero")).getAttribute("value");

		assertTrue(cpf.matches("^[0-9]{3}[.][0-9]{3}[.][0-9]{3}[-|/][0-9]{2}$"));

		Thread.sleep(2000); 
	}

}
