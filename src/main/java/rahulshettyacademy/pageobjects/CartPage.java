package rahulshettyacademy.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import rahulshettyacademy.AbstractComponents.AbstractComponent;

public class CartPage extends AbstractComponent {
//	158. Creating Page object Classes for Login Screen and migrate the test
	
	WebDriver driver;
	
	@FindBy(css=".totalRow button")
	WebElement checkoutEle;
	
	@FindBy(css=".cartSection h3")
	private List<WebElement> cartProducts;
	
//	List<WebElement> cartProducts = driver.findElements(By.cssSelector(".cartSection h3"));
//	Boolean match = cartProducts.stream().anyMatch(cartProduct->cartProduct.getText().equalsIgnoreCase(productName));
//	Assert.assertTrue(match);
//	driver.findElement(By.cssSelector(".totalRow button")).click();
	
	public CartPage(WebDriver driver) {
		super(driver); // sending "driver" from child(LandingPage) to parent(AbstractComponent)
		this.driver=driver;
		PageFactory.initElements(driver, this); // this refers to current class driver
	}
	
//	Action methods
	
	public Boolean VerifyProductDisplay(String productName) {
		Boolean match = cartProducts.stream().anyMatch(product->product.getText().equalsIgnoreCase(productName));
		return match;
	}
	
	public CheckoutPage goToCheckout() {
		checkoutEle.click();
		return new CheckoutPage(driver);
		
	}
	
	
	
	
	
	
	
	
	
	
	

}
