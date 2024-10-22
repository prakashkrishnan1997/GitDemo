package rahulshettyacademy.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import rahulshettyacademy.AbstractComponents.AbstractComponent;

public class OrderPage extends AbstractComponent {
//	158. Creating Page object Classes for Login Screen and migrate the test
	
	WebDriver driver;
	
	@FindBy(css=".totalRow button")
	WebElement checkoutEle;
	
	@FindBy(css="tr td:nth-child(3)")
	private List<WebElement> productNames;
	
//	List<WebElement> cartProducts = driver.findElements(By.cssSelector(".cartSection h3"));
//	Boolean match = cartProducts.stream().anyMatch(cartProduct->cartProduct.getText().equalsIgnoreCase(productName));
//	Assert.assertTrue(match);
//	driver.findElement(By.cssSelector(".totalRow button")).click();
	
	public OrderPage(WebDriver driver) {
		super(driver); // sending "driver" from child(LandingPage) to parent(AbstractComponent)
		this.driver=driver;
		PageFactory.initElements(driver, this); // this refers to current class driver
	}
	
//	Action methods
	
	public Boolean VerifyOrderDisplay(String productName) {
		Boolean match = productNames.stream().anyMatch(product->product.getText().equalsIgnoreCase(productName));
		return match;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	

}
