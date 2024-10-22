package rahulshettyacademy.tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import rahulshettyacademy.pageobjects.LandingPage;

import java.time.Duration;
import java.util.List;
import java.util.stream.Stream;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;


public class StandAloneTest {

	public static void main(String[] args) {
//		152. Selenium Program on  WebDriverManager - Login-  Get Products List
		
		ChromeOptions ops = new ChromeOptions();
		ops.addArguments("--remote-allow-origins=*");
		
		System.setProperty("webdriver.chrome.driver", "D:/Interview Preparation/Selenium/chromedriver-win64/chromedriver.exe");
		WebDriver driver = new ChromeDriver(ops);
		driver.manage().window().maximize();
		
		

		
//		WebDriverManager.chromedriver().setup();
//		WebDriver driver = new ChromeDriver(ops);
		String productName = "ZARA COAT 3";
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("https://rahulshettyacademy.com/client");
		
		LandingPage landingPage = new LandingPage(driver);
		
		driver.findElement(By.id("userEmail")).sendKeys("prakash97@zohomail.com");
		driver.findElement(By.id("userPassword")).sendKeys("Qwe@1234");
		driver.findElement(By.id("login")).click();
		
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3")));
		
		List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));
		
//		153. Selenium Program to retrieve product and Add to Cart based on Java Streams
		WebElement prod = products.stream().filter(product->product.findElement(By.cssSelector("b")).getText().equals(productName))
				.findFirst().orElse(null);
		
		prod.findElement(By.cssSelector(".card button:last-of-type")).click();
		
//		154. Implementation of explicit wait to handle  application synchronously  on loading
//		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
		
//		loading symbol before alert
//		class="ng-animating"
//		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".ng-animating"))); // performance issue
		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
		driver.findElement(By.cssSelector("[routerlink*='cart']")).click();
		
//		155. Logic to verify items in the cart with Streams and Checkout
		List<WebElement> cartProducts = driver.findElements(By.cssSelector(".cartSection h3"));
		Boolean match = cartProducts.stream().anyMatch(cartProduct->cartProduct.getText().equalsIgnoreCase(productName));
		Assert.assertTrue(match);
		driver.findElement(By.cssSelector(".totalRow button")).click();
		
//		156. Wrapping up end to end automation Script on Purchasing Order in Ecommerce App
		
//		My Solution
		
//		driver.findElement(By.cssSelector(".form-group input")).sendKeys("ind");
//		List<WebElement> options = driver.findElements(By.cssSelector(".list-group button"));
//		for(WebElement option:options) {
//			if(option.getText().equalsIgnoreCase("India")) {
//				option.click();
//				break;
//			}
//		}
//		
//		driver.findElement(By.cssSelector(".action__submit")).click();
		
		Actions a = new Actions(driver);
		a.sendKeys(driver.findElement(By.cssSelector("[placeholder='Select Country']")), "india").build().perform();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results")));
		
//		cssSelector: .ta-item:nth-of-type(2)
		driver.findElement(By.xpath("(//button[contains(@class,'ta-item')][2]")).click();
		driver.findElement(By.cssSelector(".action__submit")).click();
		
		String confirmMessage = driver.findElement(By.cssSelector(".hero-primary")).getText();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
		System.out.println(confirmMessage);
		driver.close();
		
		

	}

}
