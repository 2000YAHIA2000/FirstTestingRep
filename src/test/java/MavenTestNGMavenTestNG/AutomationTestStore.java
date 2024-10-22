package MavenTestNGMavenTestNG;

import org.testng.annotations.Test;
import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.openqa.selenium.support.ui.Select;
public class AutomationTestStore {
	//Yahia
	WebDriver driver = new ChromeDriver();
	String theurl = "https://automationteststore.com/";

	String[] FirstNames = { "ahmad", "ali", "anas", "omar", "ayat", "alaa", "sawsan", "Rama" };
	String[] LastNames = { "Khaled", "mustafa", "Mohammad", "abdullah", "malek", "omar" };
	Random Rand = new Random();
	String DomainName;
	String GlobalUserFirstName;
	String GlobalUserLastName;
	String Globelemail;
	String GlobelLoginEmail;
	int RandomNumberForTheEmail;
	@BeforeTest
	public void Setup() {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get(theurl);
		driver.manage().window().maximize();
	}

	@Test(priority = 1 , enabled = true)
	public void Sigup() throws InterruptedException {
		int RandomNumberForIndexFirstName = Rand.nextInt(FirstNames.length);
		int RandomNumberForIndexLastName = Rand.nextInt(LastNames.length);
		boolean RandomForEmailType = Rand.nextBoolean();
		String UserFirstName = FirstNames[RandomNumberForIndexFirstName];
		GlobalUserFirstName=UserFirstName;
		String UserLastName = LastNames[RandomNumberForIndexLastName];
		GlobalUserLastName=UserLastName;
		
		RandomNumberForTheEmail = Rand.nextInt(564548);
		if (RandomForEmailType == false) {
			DomainName = "@gmail.com";
		} else {
			DomainName = "@yahoo.com";
		}
		Globelemail = UserFirstName + UserLastName + RandomNumberForTheEmail + DomainName;
		WebElement SignupButton = driver.findElement(By.xpath("//*[@id=\"customer_menu_top\"]/li/a"));
		SignupButton.click();
		WebElement ContinueRegisterButton = driver.findElement(By.xpath("//button[@title='Continue']"));
		ContinueRegisterButton.click();
		WebElement FirsetNameInput = driver.findElement(By.id("AccountFrm_firstname"));
		FirsetNameInput.sendKeys(UserFirstName);
		WebElement LastNameInput = driver.findElement(By.id("AccountFrm_lastname"));
		LastNameInput.sendKeys(UserLastName);
		WebElement EmailInput = driver.findElement(By.id("AccountFrm_email"));
		EmailInput.sendKeys(Globelemail);
		WebElement AddressInput = driver.findElement(By.id("AccountFrm_address_1"));
		AddressInput.sendKeys("khaldea");
		WebElement CityInput = driver.findElement(By.id("AccountFrm_city"));
		CityInput.sendKeys("Amman");
		WebElement CountryInput = driver.findElement(By.id("AccountFrm_country_id"));
		int RandomCountry = Rand.nextInt(1, 240);
		Select selector1 = new Select(CountryInput);
		selector1.selectByIndex(RandomCountry);
		Thread.sleep(3000);
		WebElement ZoneInput = driver.findElement(By.id("AccountFrm_zone_id"));
		Select selector2 = new Select(ZoneInput);
		int RandomZone = Rand.nextInt(1, 3);
		selector2.selectByIndex(RandomZone);
		WebElement PostCodeInput = driver.findElement(By.id("AccountFrm_postcode"));
		PostCodeInput.sendKeys("5557");
		WebElement loginNameInput = driver.findElement(By.id("AccountFrm_loginname"));
		loginNameInput.sendKeys(GlobalUserFirstName+GlobalUserLastName+RandomNumberForTheEmail);
		WebElement passwordInput = driver.findElement(By.id("AccountFrm_password"));
		passwordInput.sendKeys("123456789");
		WebElement confirmpasswordInput = driver.findElement(By.id("AccountFrm_confirm"));
		confirmpasswordInput.sendKeys("123456789");
		WebElement agreeCheckbox = driver.findElement(By.id("AccountFrm_agree"));
		if (!agreeCheckbox.isSelected()) {
			agreeCheckbox.click();
		}//Login name must be alphanumeric only and between 5 and 64 characters!
		WebElement FinshButton = driver.findElement(By.xpath("//Button[@title='Continue']"));
		FinshButton.click();
	}

	@Test(priority = 2, enabled = true)
	public void Logout() throws InterruptedException {
		Thread.sleep(3000);
		WebElement Navgate=driver.findElement(By.id("customer_menu_top"));
		Actions actions=new Actions(driver);
		actions.moveToElement(Navgate).perform();
		WebElement logout=driver.findElement(By.linkText("Not "+GlobalUserFirstName+"? Logoff"));
		logout.click();
	}
	@Test(priority = 3, enabled = true)
	public void Login() {
		WebElement SignupButton = driver.findElement(By.xpath("//*[@id=\"customer_menu_top\"]/li/a"));
		SignupButton.click();
		WebElement UserNaameInput=driver.findElement(By.id("loginFrm_loginname"));
		UserNaameInput.sendKeys(GlobalUserFirstName+GlobalUserLastName+RandomNumberForTheEmail);
		WebElement PasswordInput=driver.findElement(By.id("loginFrm_password"));
		PasswordInput.sendKeys("123456789");
		WebElement LoginButton=driver.findElement(By.xpath("//button[@title='Login']"));
		LoginButton.click();
	}
	@Test(priority = 4,enabled = true)
	public void choseRandomProduct() throws InterruptedException {
		//this code to chose randomly from Main category 
		List <WebElement> CategoryMenu=driver.findElements(By.cssSelector("ul.categorymenu > li "));
		int RandomNumberToChoesFromMainCategory=Rand.nextInt(1,CategoryMenu.size());
		Actions actions=new Actions(driver);
		actions.moveToElement(CategoryMenu.get(RandomNumberToChoesFromMainCategory)).perform();
		/*This code will convert the text in this category we have chosen it. to list of string then  find the sub using
		linkText*/
		String SubCategory=CategoryMenu.get(RandomNumberToChoesFromMainCategory).getText();
		List<String> itemList = Arrays.stream(SubCategory.split("\n")).map(String::trim).toList();
		int RandomNumberToChoseFromSubcategory=Rand.nextInt(1,itemList.size());
		WebElement ChoseFromSubcategory=driver.findElement(By.linkText(itemList.get(RandomNumberToChoseFromSubcategory)));
		ChoseFromSubcategory.click(); 
	}
	@Test(priority = 5, enabled = true)
	public void addToCart() throws InterruptedException {
		Thread.sleep(1000);
	    WebElement Countaerproducts = driver.findElement(By.cssSelector(".thumbnails.grid.row.list-inline"));
	    List<WebElement> ListOfProducts=Countaerproducts.findElements(By.cssSelector(".thumbnail"));
	    int randomProductIndex = Rand.nextInt(ListOfProducts.size());
	    if(ListOfProducts.isEmpty()) {
	    	
	    	 System.out.println("produts is empty");
	    }
	    else
	   
	    ListOfProducts.get(randomProductIndex).click();
	    WebElement addCartButton = driver.findElement(By.className("productpagecart"));
	    if (addCartButton.getText().equals("Add to Cart")) {
	    	 try {
					WebElement SizesCounater=driver.findElement(By.cssSelector(".input-group[class*=col-]"));
					List<WebElement> ListOfSize=SizesCounater.findElements(By.tagName("input"));
					int randomProductIndexForSize = Rand.nextInt(ListOfSize.size());
					ListOfSize.get(randomProductIndexForSize).click();
					}
			    	catch (Exception e) {
						System.out.println("no size avilbele");
					}
	        addCartButton.click();
	    } else {
	        choseRandomProduct();
	        addToCart(); // Recursive call if the product cannot be added
	    }
	}
	@Test(priority = 6)
	public void checkOut() {
		
		WebElement CheckoutButton=driver.findElement(By.id("cart_checkout1"));
		CheckoutButton.click();
	}
	@Test(priority = 7)
	public void confiram() {
		WebElement confriamButton=driver.findElement(By.id("checkout_btn"));
		confriamButton.click();
	}
	
	@AfterTest
	public void Finsh() {
driver.close();
	}
}
