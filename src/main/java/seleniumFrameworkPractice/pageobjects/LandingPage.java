package seleniumFrameworkPractice.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import seleniumFrameworkPractice.AbastractComponents.AbstractComponents; 

public class LandingPage extends AbstractComponents{  //inhereitence is used to whatever variables, methods, fields declared in this class will be automatically aplicable/reuse by landing class 
	
	WebDriver driver; //Even after initialization there's no life.
	
	public LandingPage(WebDriver driver)  //Constructor takes same name has classname and that will be the first method to execute when we touch our class. If object of this class is done constructor is initialized.
	{                     //when we are calling arguments we can catch in constructor and it should be defined here
		super(driver);
		this.driver=driver; //1st driver is from this class and 2nd is from mainclass(standalone). This driver still has no actions so create an object of this class in mainclass.
		PageFactory.initElements(driver, this);
	}

	//WebElement userEmail = driver.findElement(By.id("userEmail")); //This driver initially does not have any life, it is simply null object. From parent class driver should be sent to this class so we use constructor.
	                                                               //this driver here refers to local driver.
	//design pattern called PageFactor - by using that we can reduce the syntax of creating a webelement. There's anotation called @FindBy
	
	@FindBy(id = "userEmail") //since we do not have a driver here, we initilize in constructor.
	WebElement userEmail;
	
	@FindBy(id="userPassword")
	WebElement userPassword;
	
	@FindBy(id="login")
	WebElement submit;
	
	//ActionMethods: Instead of writing everything we define in simple words.
	public ProductCatalogue loginApplication(String email, String password) {   //This should not be harcoded. pageobject should not hold any data, it should only focus on elements and actions.
		userEmail.sendKeys(email);
		userPassword.sendKeys(password);
		submit.click();
		ProductCatalogue productCatalogue = new ProductCatalogue(driver);
		return productCatalogue;
		
		
		
		
	}
	
	public void goTo() {
		driver.get("https://rahulshettyacademy.com/client/");
	}
	
	
	

	}


