package seleniumFrameworkPractice.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import seleniumFrameworkPractice.AbastractComponents.AbstractComponents;

public class CheckoutPage extends AbstractComponents {
	
	WebDriver driver;
	
	public CheckoutPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
		
	}
	
	@FindBy(css= "action__submit")
	WebElement submit;
	
	@FindBy(css= "[placeholder = 'Select Country']")
	WebElement country;
	
	@FindBy(css= "[placeholder = 'Select Country']")
	WebElement country;

}
