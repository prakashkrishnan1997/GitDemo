package rahulshettyacademy.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import rahulshettyacademy.AbstractComponents.AbstractComponent;

public class LandingPage extends AbstractComponent {
//	158. Creating Page object Classes for Login Screen and migrate the test
	
	WebDriver driver;
	
	public LandingPage(WebDriver driver) {
		super(driver); // sending "driver" from child(LandingPage) to parent(AbstractComponent)
		this.driver=driver;
		PageFactory.initElements(driver, this); // this refers to current class driver
	}
	
//	WebElement userEmail = driver.findElement(By.id("userEmail"));
	
//	Page Factory
	@FindBy(id="userEmail")
	WebElement userEmail;
	
	@FindBy(id="userPassword")
	WebElement passwordEle;
	
	@FindBy(id="login")
	WebElement submit;
	
	@FindBy(css="[class*='flyInOut']")
	WebElement errorMessage;
	
	
	
//	159. Implementing Action methods for Page factory web elements to implement logic
	
//	Action methods	
	public ProductCatalogue loginApplication(String email,String password) {
		userEmail.sendKeys(email);
		passwordEle.sendKeys(password);
		submit.click();
		ProductCatalogue productCatalogue = new ProductCatalogue(driver);
		return productCatalogue;
	}
	
	public String getErrorMessage() {
		waitForWebElementToAppear(errorMessage);
		return errorMessage.getText();
	}
	
	public void goTo() {
		driver.get("https://rahulshettyacademy.com/client");
	}
	
	
	
	
	
	
	
	
	
	
	

}
