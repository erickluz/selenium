import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class TesteCampoTreinamento {

	private static WebDriver navegador;
	
	@Before
	public void setup() {
		System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/src/main/resources/chromedriver.exe");
		navegador = new ChromeDriver();
		navegador.manage().window().setSize(new Dimension(1200, 765));
		navegador.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
	}
	
	@Test
	public void testeTextField(){		
		navegador.findElement(By.id("elementosForm:nome")).sendKeys("Teste de escrita");
		Assert.assertEquals("Teste de escrita", navegador.findElement(By.id("elementosForm:nome")).getAttribute("value"));
		navegador.quit();
	}
	
	@Test
	public void deveIntegarirComTextArea(){
		navegador.findElement(By.id("elementosForm:sugestoes")).sendKeys("teste\n\naasldjdlks\nUltima linha");
		Assert.assertEquals("teste\n\naasldjdlks\nUltima linha", navegador.findElement(By.id("elementosForm:sugestoes")).getAttribute("value"));
		navegador.quit();
	}
	
	@Test
	public void deveIntegarirComRadioButton(){
		navegador.findElement(By.id("elementosForm:sexo:0")).click();
		Assert.assertTrue(navegador.findElement(By.id("elementosForm:sexo:0")).isSelected());
		navegador.quit();
	}
	
	@Test
	public void deveIntegarirComCheckbox(){
		navegador.findElement(By.id("elementosForm:comidaFavorita:2")).click();
		Assert.assertTrue(navegador.findElement(By.id("elementosForm:comidaFavorita:2")).isSelected());
		navegador.quit();
	}
	
	@Test
	public void deveIntegarirComCombo(){
		WebElement element = navegador.findElement(By.id("elementosForm:escolaridade"));
		Select combo = new Select(element);
//		combo.selectByIndex(2);
//		combo.selectByValue("superior");
		combo.selectByVisibleText("2o grau completo");
		
		Assert.assertEquals("2o grau completo", combo.getFirstSelectedOption().getText());
		navegador.quit();
	}
	
	@Test
	public void deveVerificarValoresCombo(){
		WebElement element = navegador.findElement(By.id("elementosForm:escolaridade"));
		Select combo = new Select(element);
		List<WebElement> options = combo.getOptions();
		Assert.assertEquals(8, options.size());
		
		boolean encontrou = false;
		for(WebElement option: options) {
			if(option.getText().equals("Mestrado")){
				encontrou = true;
				break;
			}
		}
		Assert.assertTrue(encontrou);
		navegador.quit();
	}
	
	@Test
	public void deveVerificarValoresComboMultiplo(){
		WebElement element = navegador.findElement(By.id("elementosForm:esportes"));
		Select combo = new Select(element);
		combo.selectByVisibleText("Natacao");
		combo.selectByVisibleText("Corrida");
		combo.selectByVisibleText("O que eh esporte?");
		
		List<WebElement> allSelectedOptions = combo.getAllSelectedOptions();
		Assert.assertEquals(3, allSelectedOptions.size());
		
		combo.deselectByVisibleText("Corrida");
		allSelectedOptions = combo.getAllSelectedOptions();
		Assert.assertEquals(2, allSelectedOptions.size());
		navegador.quit();
	}
	
	@Test
	public void deveInteragirComBotoes(){
		WebElement botao = navegador.findElement(By.id("buttonSimple"));
		botao.click();
		
		Assert.assertEquals("Obrigado!", botao.getAttribute("value"));
		navegador.quit();
	}
	
	@Test
	@Ignore
	public void deveInteragirComLinks(){
		WebElement element = navegador.findElement(By.linkText("Voltar"));
		element.click();
	}
	
	@Test
	public void deveBuscarTextosNaPagina(){	
		WebElement classe = navegador.findElement(By.className("facilAchar"));		
		//Assert.assertTrue(body.getText().contains("Campo de Treinamento"));
		//Assert.assertEquals("Campo de Treinamento", h3.getText());
		Assert.assertEquals("Cuidado onde clica, muitas armadilhas...", classe.getText());
		navegador.quit();
	}
	
	@Test
	public void deveCadastrar() {
		navegador.findElement(By.id("elementosForm:nome")).sendKeys("Erick");
		navegador.findElement(By.id("elementosForm:sobrenome")).sendKeys("Luz");
		navegador.findElement(By.id("elementosForm:sexo:0")).click();
		navegador.findElement(By.id("elementosForm:comidaFavorita:0")).click();
		navegador.findElement(By.id("elementosForm:comidaFavorita:2")).click();
		Select comboEscolaridade = new Select(navegador.findElement(By.id("elementosForm:escolaridade")));
		comboEscolaridade.selectByVisibleText("Superior");	
		Select comboEsportes = new Select(navegador.findElement(By.id("elementosForm:esportes")));
		comboEsportes.selectByVisibleText("Natacao");
		comboEsportes.selectByVisibleText("Futebol");
		navegador.findElement(By.id("elementosForm:sugestoes")).sendKeys("Mudar todo o cadastro");

		navegador.findElement(By.id("elementosForm:cadastrar")).click();
		
		Assert.assertTrue(navegador.findElement(By.id("resultado")).getText().startsWith("Cadastrado!"));
		Assert.assertTrue(navegador.findElement(By.id("descNome")).getText().endsWith("Erick"));
		Assert.assertTrue(navegador.findElement(By.id("descSobrenome")).getText().endsWith("Luz"));
		Assert.assertTrue(navegador.findElement(By.id("descComida")).getText().endsWith("Carne Pizza"));
		Assert.assertTrue(navegador.findElement(By.id("descEscolaridade")).getText().endsWith("superior"));
		Assert.assertTrue(navegador.findElement(By.id("descEsportes")).getText().endsWith("Natacao Futebol"));
		Assert.assertTrue(navegador.findElement(By.id("descSugestoes")).getText().endsWith("Mudar todo o cadastro"));

		navegador.quit();
	}
	
}


