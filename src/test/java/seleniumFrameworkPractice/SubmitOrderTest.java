package seleniumFrameworkPractice;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import io.github.bonigarcia.wdm.WebDriverManager;
import seleniumFrameworkPractice.pageobjects.LandingPage;
import seleniumFrameworkPractice.pageobjects.ProductCatalogue;
import seleniumFrameworkPractice.pageobjects.cartPage;

public class SubmitOrderTest { //I'm writing to practice Git

	private static FluentWait<WebDriver> wait;

	public static void main(String[] args) throws InterruptedException { //Before creating a class make sure all the repositories are addeded to pom.xml (dependencies like, maven, testng, webdrivers as well).
		
		
		String productName = "ZARA COAT 3";
		WebDriverManager.chromedriver().setup(); //We earlier use to write entire setup but now only by WebDriverManager it is enough.
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));//implicit wait is globally declared
		driver.manage().window().maximize();
		driver.get("https://rahulshettyacademy.com/client/");
		LandingPage landingPage = new LandingPage (driver);
		landingPage.goTo();
		ProductCatalogue productCatalogue = landingPage.loginApplication("nirmalapawar.v@gmail.com", "Ravnir@007");
		
		List<WebElement>products =productCatalogue.getProductList();
		productCatalogue.addProductToCart(productName);
		CartPage cartPage = productCatalogue.goToCartPage();
		Boolean match = cartPage.VerifyProductDisplay(productName);
		Assert.assertTrue(match);
		cartPage.goToCheckout();
		
		 
		driver.findElement(By.cssSelector(".totalRow button")).click();
		
		Actions a = new Actions(driver);
		a.sendKeys(driver.findElement(By.cssSelector("[placeholder='Select Country']")), "india").build().perform();
		//Thread.sleep(2000);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results")));

		driver.findElement(By.xpath("(//button[contains(@class,'ta-item')])[2]")).click();
		driver.findElement(By.cssSelector(".action__submit")).click();
		
		String confirmMessage = driver.findElement(By.cssSelector(".hero-primary")).getText();
		confirmMessage.equalsIgnoreCase("Thankyou for the order."); 
        Assert.assertTrue(confirmMessage.equalsIgnoreCase("Thankyou for the order."));
		
       driver.quit();
	}

}
