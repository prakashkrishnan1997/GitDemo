package rahulshettyacademy.tests;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import io.github.bonigarcia.wdm.WebDriverManager;
import rahulshettyacademy.TestComponents.BaseTest;
import rahulshettyacademy.pageobjects.CartPage;
import rahulshettyacademy.pageobjects.CheckoutPage;
import rahulshettyacademy.pageobjects.ConfirmationPage;
import rahulshettyacademy.pageobjects.LandingPage;
import rahulshettyacademy.pageobjects.OrderPage;
import rahulshettyacademy.pageobjects.ProductCatalogue;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Stream;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;


public class SubmitOrderTest extends BaseTest {
	
	String productName = "ZARA COAT 3";
	
	@Test(dataProvider="getData",groups= {"Purchase"})
	public void submitOrder(HashMap<String,String> input) throws IOException, InterruptedException {
//		152. Selenium Program on  WebDriverManager - Login-  Get Products List		
		
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));
		ProductCatalogue productCatalogue = landingPage.loginApplication(input.get("email"), input.get("password"));	
		List<WebElement> products = productCatalogue.getProductList();	
		productCatalogue.addProductToCart(input.get("product"));
		CartPage cartPage = productCatalogue.goToCartPage();
		Boolean match = cartPage.VerifyProductDisplay(input.get("product"));	
		Assert.assertTrue(match);		
		CheckoutPage checkoutPage = cartPage.goToCheckout();	
		checkoutPage.selectCountry("india");
		ConfirmationPage confirmationPage = checkoutPage.submitOrder();		
		String confirmMessage = confirmationPage.getConfirmationMessage();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
		System.out.println(confirmMessage);
		
		

	}
	
//	To verify ZARA COAT 3 is displaying in orders page
	@Test(dependsOnMethods= {"submitOrder"})
	public void OrderHistoryTest() {
		ProductCatalogue productCatalogue = landingPage.loginApplication("prakash97@zohomail.com", "Qwe@1234");
		OrderPage ordersPage = productCatalogue.goToOrdersPage();
		Assert.assertTrue(ordersPage.VerifyOrderDisplay(productName));
	}
	
	
	
	
	
	
//	How to derive data from JSON file (Data Provider)
	@DataProvider
	public Object[][] getData() throws IOException {
		
//		HashMap<String,String> map = new HashMap<String,String>();
//		map.put("email", "prakash97@zohomail.com");
//		map.put("password", "Qwe@1234");
//		map.put("product", "ZARA COAT 3");
//		
//		HashMap<String,String> map1 = new HashMap<String,String>();
//		map1.put("email", "prakash79@zohomail.com");
//		map1.put("password", "Qaz@1234");
//		map1.put("product", "ADIDAS ORIGINAL");
		
		List<HashMap<String,String>> data = getJsonDataToMap(System.getProperty("user.dir") + "//src//test//java//rahulshettyacademy//data//PurchaseOrder.json");
		return new Object[][] {{data.get(0)}, {data.get(1)}};
		
	}
	
	

	
	

}
