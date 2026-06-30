package seleniumFrameworkPractice.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import seleniumFrameworkPractice.AbastractComponents.AbstractComponents;

public class ProductCatalogue extends AbstractComponents {
	
	WebDriver driver; //Even after initialization there's no life.
	
	public ProductCatalogue(WebDriver driver)  //Constructor takes same name has classname and that will be the first method to execute when we touch our class. If object of this class is done constructor is initialized.
	{                                          //when we are calling arguments we can catch in constructor and it should be defined here
		super(driver);  //everychild class should have this, to access the parents class
		
		this.driver=driver; //1st driver is from this class and 2nd is from mainclass(standalone). This driver still has no actions so create an object of this class in mainclass.
		PageFactory.initElements(driver, this);
	}
	
	//List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));

	
	@FindBy(css = ".mb-3") //since we do not have a driver here, we initilize in constructor.
	List<WebElement> products;
	
	@FindBy(css = ".ng-animating") //since we do not have a driver here, we initilize in constructor.
	WebElement spinner;
	
	
	By productsBy = By.cssSelector(".mb-3");
	By addToCart = By.cssSelector(".card-body button:last-of-type");
	By toastMessage = (By.cssSelector("#toast-container"));
	public List<WebElement> getProductList() 
	{
		
		waitForElementToAppear(productsBy);
		return products;
	}
	
	public WebElement getProductByName(String productName)
	{

		WebElement prod =  getProductList().stream().filter(product->                       // If we need complete webelemenent then Filter is used or else use anyMatch(if we need to check only if the condition matches).
		product.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst().orElse(null); //stream itrtates to each element.
		return prod;
	}

	public void addProductToCart(String productName)
	{
		WebElement prod =  getProductByName(productName);
		prod.findElement(addToCart).click();
		waitForElementToAppear(toastMessage);
		//waitForElementToDisappear(spinner);
	}
	}


