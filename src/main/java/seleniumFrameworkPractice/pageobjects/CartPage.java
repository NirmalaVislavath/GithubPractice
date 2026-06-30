

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import seleniumFrameworkPractice.AbastractComponents.AbstractComponents;

public class CartPage extends AbstractComponents {
	
	WebDriver driver;
	
	@FindBy(css = ".totalRow button") //since we do not have a driver here, we initilize in constructor.
	WebElement checkoutEle;
	
	@FindBy(css = ".cartSection h3") //since we do not have a driver here, we initilize in constructor.
	private List<WebElement> cartProducts;
	
	
	public CartPage(WebDriver driver)  //Constructor takes same name has classname and that will be the first method to execute when we touch our class. If object of this class is done constructor is initialized.
	{                                          //when we are calling arguments we can catch in constructor and it should be defined here
		super(driver);  //everychild class should have this, to access the parents class
		
		this.driver=driver; //1st driver is from this class and 2nd is from mainclass(standalone). This driver still has no actions so create an object of this class in mainclass.
		PageFactory.initElements(driver, this);
	}
	

	
	public Boolean VerifyProductDisplay(String productName)
	{

		Boolean match =  cartProducts.stream().anyMatch(product->                       // If we need complete webelemenent then Filter is used or else use anyMatch(if we need to check only if the condition matches).
		product.getText().equalsIgnoreCase(productName));
		return match;
	}

	public void goToCheckout()
	{
		checkoutEle.click();
		return new CheckoutPage();
	}
	}
	


