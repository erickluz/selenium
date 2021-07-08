import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class TesteAlert {
	private static WebDriver navegador;
	
	@Before
	public void setup() {
		System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/src/main/resources/chromedriver.exe");
		navegador = new ChromeDriver();
		navegador.manage().window().setSize(new Dimension(1200, 765));
		navegador.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
	}
	
	@Test
	public void deveInteragirComAlertSimples(){		
		navegador.findElement(By.id("alert")).click();
		Alert alert = navegador.switchTo().alert();
		Assert.assertEquals("Alert Simples", alert.getText());
		String textoAlerta = alert.getText();
		alert.accept();
		
		navegador.findElement(By.id("elementosForm:nome")).sendKeys(textoAlerta);
		
		navegador.quit();
	}
	
	@Test
	public void deveInteragirComAlertConfirmar(){		
		navegador.findElement(By.id("confirm")).click();
		Alert alert = navegador.switchTo().alert();
		Assert.assertEquals("Confirm Simples", alert.getText());
		alert.accept();
		alert = navegador.switchTo().alert();
		Assert.assertEquals("Confirmado", alert.getText());		 
		navegador.quit();
	}
	
	@Test
	public void deveInteragirComAlertCancelar(){		
		navegador.findElement(By.id("confirm")).click();
		Alert alert = navegador.switchTo().alert();
		Assert.assertEquals("Confirm Simples", alert.getText());
		alert.dismiss();
		alert = navegador.switchTo().alert();
		Assert.assertEquals("Negado", alert.getText());		 
		navegador.quit();
	}
	
	@Test
	public void deveInteragirComAlertPromptAcertando(){		
		navegador.findElement(By.id("prompt")).click();
		Alert alert = navegador.switchTo().alert();
		Assert.assertEquals("Digite um numero", alert.getText());
		alert.sendKeys("1");
		alert.accept();
		Assert.assertEquals("Era 1?", alert.getText());
		alert.accept();
		Assert.assertEquals(":D", alert.getText());
		navegador.quit();
	}
	@Test
	public void deveInteragirComAlertPromptErrando(){
		navegador.findElement(By.id("prompt")).click();
		Alert alert = navegador.switchTo().alert();
		Assert.assertEquals("Digite um numero", alert.getText());
		alert.sendKeys("12");
		alert.accept();
		Assert.assertEquals("Era 12?", alert.getText());
		alert.dismiss();
		Assert.assertEquals(":(", alert.getText());
		navegador.quit();
	}
}
