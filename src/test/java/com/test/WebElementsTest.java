package com.test;

import static org.junit.Assert.*;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import com.categorias.Alta;
import com.categorias.Baixa;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class WebElementsTest {

	private WebDriver driver;

	@Before
	public void setUp() throws Exception {

		//System.out.println("inicio");

		System.setProperty("webdriver.chrome.driver", "C:\\Pamela\\drivers\\chromedriver.exe");

		driver = new ChromeDriver();

		driver.get("http://antoniotrindade.com.br/treinoautomacao/elementsweb.html");

		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);

		driver.manage().window().maximize();
	}

	@After
	public void tearDown() throws Exception {
		//System.out.println("fim");

		driver.quit();
	}


	@Test @Category(Alta.class)
	public void validaNomeProprio() throws InterruptedException {
		//System.out.println("Primeiro teste");
		WebElement txtName = driver.findElement(By.name("txtbox1"));
		txtName.sendKeys("Pamela");
		Thread.sleep(2000);
		assertEquals("Deveria ter o nome","Pamela", txtName.getAttribute("value"));
	}

	@Test
	public void validaTextFieldDesabilitado() {
		//System.out.println("Segundo teste");
		WebElement txtName = driver.findElement(By.name("txtbox1"));
		WebElement txtName2 = driver.findElement(By.name("txtbox2"));

		assertTrue("Campo habilitado",txtName.isEnabled());
		assertFalse("Campo desabilitado",txtName2.isEnabled());

	}

	@Test @Category(Baixa.class)
	public void validaRadioGroup() {

		List<WebElement> listRadio = driver.findElements(By.name("radioGroup1"));
		assertEquals(4, listRadio.size());

		/*
		listRadio.get(2).click();
		assertTrue(listRadio.get(2).isSelected());
		 */

		for(WebElement webElement : listRadio) {
			if (webElement.getAttribute("value").equals("Radio Button 3 selecionado")) {
				webElement.click();
				break;
			}

		}
		assertTrue(listRadio.get(2).isSelected());
	}

	@Test  @Category(Baixa.class)
	public void validaCheckbox() throws InterruptedException {

		List<WebElement> listCheckbox = driver.findElements(By.name("chkbox"));
		assertEquals(4, listCheckbox.size());

		/*
		listRadio.get(2).click();
		assertTrue(listRadio.get(2).isSelected());
		 */

		for(WebElement webElement : listCheckbox) {
			if (webElement.getAttribute("value").equals("Check Box 3 selecionado") 
					|| webElement.getAttribute("value").equals("Check Box 4 selecionado")) {
				webElement.click();
				//listCheckbox.get(3).click();
				//break;

			}

		}
		/*Não colocar assert dentro de if pq se a condição não for atendida, 
		 * a verificação nunca vai ser feita
		 */
		assertTrue(listCheckbox.get(2).isSelected());
		assertTrue(listCheckbox.get(3).isSelected());
		Thread.sleep(5000);
	}

	@Test
	public void validaListBoxSingle() throws InterruptedException {

		WebElement listBoxSingle = driver.findElement(By.name("dropdownlist"));

		Select selectListBoxSingle = new Select(listBoxSingle);

		assertEquals(10, selectListBoxSingle.getOptions().size());

		selectListBoxSingle.selectByIndex(5);
		Thread.sleep(2000);
		selectListBoxSingle.selectByVisibleText("Item 7");
		Thread.sleep(2000);

		assertEquals("Item 7", selectListBoxSingle.getFirstSelectedOption().getText());

	}	

	@Test
	public void validaListBoxMulti() throws InterruptedException {

		WebElement listBoxMulti = driver.findElement(By.name("multiselectdropdown"));

		Select selectListBoxMulti = new Select(listBoxMulti);

		assertEquals(10, selectListBoxMulti.getOptions().size());

		selectListBoxMulti.selectByIndex(4);
		Thread.sleep(2000);
		selectListBoxMulti.selectByIndex(7);
		//selectListBoxMulti.selectByVisibleText("Item 7");
		Thread.sleep(2000);
		selectListBoxMulti.selectByIndex(8);
		Thread.sleep(2000);

		List<WebElement> listSelecionados= selectListBoxMulti.getAllSelectedOptions();

		//for(WebElement webElement : listSelecionados) {
		assertEquals(3, listSelecionados.size());
		assertEquals("Item 5", listSelecionados.get(0).getText());
		assertEquals("Item 8", listSelecionados.get(1).getText());
		assertEquals("Item 9", listSelecionados.get(2).getText());
		//}

		/*assertEquals("Item 5", selectListBoxMulti.getFirstSelectedOption().getText());
		assertEquals("Item 8", selectListBoxMulti.getAllSelectedOptions()
				FirstSelectedOption().getText());
		assertEquals("Item 10", selectListBoxMulti.getFirstSelectedOption().getText());
		 */
	}

	@Test @Category(Alta.class)
	public void navegaIframe() throws InterruptedException {
		//System.out.println("Primeiro teste");

		//entrar no iframe
		driver.switchTo().frame("iframe_b");

		//digitar texto
		WebElement txtName = driver.findElement(By.name("s"));
		txtName.sendKeys("Pamela");

		Thread.sleep(2000);

		//validar texto
		assertEquals("Deveria ter o nome","Pamela", txtName.getAttribute("value"));

		//sair do iframe
		driver.switchTo().defaultContent();

		//txtName.sendKeys("Pamela");
		//assertEquals("Deveria ter o nome","Pamela", txtName.getAttribute("value"));

		//Segunda parte

		//entrar no iframe
		driver.switchTo().frame("iframe_d");

		//clicar no menu
		driver.findElement(By.id("dropdownButton")).click();

		//digitar texto
		WebElement txtName2 = driver.findElement(By.name("search"));
		txtName2.sendKeys("Pamela");

		Thread.sleep(2000);

		//validar texto
		assertEquals("Deveria ter o nome","Pamela", txtName2.getAttribute("value"));

	}

	@Test
	public void validaMensagemAlert() throws InterruptedException {

		WebElement btnAlert = driver.findElement(By.name("alertbtn"));

		btnAlert.click();

		Alert alert = driver.switchTo().alert();

		Thread.sleep(2000);

		assertEquals("Eu sou um alerta!", alert.getText());

		alert.accept();

		Thread.sleep(2000);

	}

	@Test  @Category({Baixa.class, Alta.class})
	public void validaMensagemPrompt() throws InterruptedException {

		WebElement btnPrompt = driver.findElement(By.name("promptbtn"));

		btnPrompt.click();

		Alert alert = driver.switchTo().alert();

		Thread.sleep(2000);

		assertEquals("Por favor, insira seu nome:", alert.getText());

		alert.dismiss();

		Thread.sleep(2000);

	}	

}
