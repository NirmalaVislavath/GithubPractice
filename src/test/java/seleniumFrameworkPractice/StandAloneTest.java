package seleniumFrameworkPractice;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import io.github.bonigarcia.wdm.WebDriverManager;
import seleniumFrameworkPractice.pageobjects.LandingPage;

public class StandAloneTest {

	public static void main(String[] args) throws InterruptedException { //Before creating a class make sure all the repositories are addeded to pom.xml (dependencies like, maven, testng, webdrivers as well).
		
		
		String productName = "ZARA COAT 3";
		WebDriverManager.chromedriver().setup(); //We earlier use to write entire setup but now only by WebDriverManager it is enough.
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));//implicit wait is globally declared
		driver.manage().window().maximize();
		driver.get("https://rahulshettyacademy.com/client/");
		LandingPage landingPage = new LandingPage (driver);
		
		driver.findElement(By.id("userEmail")).sendKeys("nirmalapawar.v@gmail.com");
		driver.findElement(By.id("userPassword")).sendKeys("Ravnir@007");
		driver.findElement(By.id("login")).click();
		
		List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));
		
		WebElement prod = products.stream().filter(product->                       // If we need complete webelemenent then Filter is used or else use anyMatch(if we need to check only if the condition matches).
		product.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst().orElse(null); //stream itrtates to each element.
		
		prod.findElement(By.cssSelector(".card-body button:last-of-type")).click();
		
		
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10)); //for explicit wait it requires to create a WEbDriverWait class.
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container"))); //To create a 'css' from ID it just #element.
		//wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating")))); //This is for an invisible/blank page. //If you don't get this locator ID in such cases we can directly check with the developers for that id.
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("[routerlink*='cart']")).click();
		 
		List<WebElement> cartProduct = driver.findElements(By.cssSelector(".cartSection h3"));
	    Boolean match =	cartProduct.stream().anyMatch(cartProducts-> cartProducts.getText().equalsIgnoreCase(productName));
		Assert.assertTrue(match);
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
		
       driver.close();
	}

}
