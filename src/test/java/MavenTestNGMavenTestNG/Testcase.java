package MavenTestNGMavenTestNG;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
public class Testcase {
	WebDriver driver;
	JavascriptExecutor js;
	///Random random = new Random();
	//Faker faker = new Faker();
	String email = "heyahia8800@gmail.com";
	String password = "123456789";
	Random random;
	@BeforeTest
	public void setup() {
		driver = new ChromeDriver();
		js = (JavascriptExecutor) driver;
		driver.get("http://139.162.190.40/");
		driver.manage().window().maximize();
	}

	@Test(priority = 1)
	public void login() throws InterruptedException {
		
		WebElement button = driver.findElement(By.id("login"));
		button.click();
		Thread.sleep(3000);
		WebElement theEmail=driver.findElement(By.id("email"));
		theEmail.sendKeys(email);

		WebElement thePassword = driver.findElement(By.id("password"));
		thePassword.sendKeys(password);

		WebElement finish = driver.findElement(By.xpath("/html/body/div[1]/nav/div[1]/div/div/div[4]/div/div[2]/div/div[2]/div/div[2]/div[1]/div/div[1]/form/div[4]/button"));
		finish.click();
	
	}
	
	public void Scrolldown() {
		js.executeScript("window.scrollTo(0, document.body.scrollHeight);");
	}

	@Test(priority = 2)
	public void chooseRandomServers() throws InterruptedException {
		Thread.sleep(3000);
		Scrolldown();
		random = new Random();
	    int randomNumber = random.nextInt(8) ; 
		Thread.sleep(3000);
		List<WebElement> randomServer = driver.findElements(By.className("text-ellipsis"));
		randomServer.get(randomNumber).click();
		
	}

	@Test(priority = 3)
	public void enterInstruction() throws InterruptedException {
		// Wait for the instructions field to be visible
		Thread.sleep(3000);
		WebElement instructionsField =driver.findElement(By.id("instructions"));
		String text = "tset";
		instructionsField.sendKeys(text);
		WebElement finish = driver.findElement(By.xpath("/html/body/div[1]/main/div/section/div/div[2]/form/div[2]/button[1]"));
		finish.click();
	}
	@AfterTest
	public void end() {
		/*if (driver != null) {
			driver.quit(); 
		}*/
	}
}
