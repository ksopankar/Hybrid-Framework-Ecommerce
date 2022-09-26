package AutomationPracticeFramework.com.Ecommerce_framework;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import io.github.bonigarcia.wdm.WebDriverManager;

public class stand_Alonetesting {

	public static void main(String[] args) {

		String name = "ADIDAS ORIGINAL";
		String countryname = "india";
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		
		driver.get("https://rahulshettyacademy.com/client");// hit url

		// get login
		driver.findElement(By.id("userEmail")).sendKeys("Kamlesh@gmail.com");
		driver.findElement(By.id("userPassword")).sendKeys("Kam12345");
		driver.findElement(By.id("login")).click();

		// explicit wait to load the site
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3")));

		// get item to cart
		List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));
//
//		for (int i = 0; i < products.size(); i++) {
//			String prod = products.get(i).findElement(By.cssSelector("b")).getText();
//			if (prod.contains(name)) {
//				driver.findElements(By.cssSelector(".card-body button:last-of-type")).get(i).click();
//			}
//
//		}

		// By java streams get item to cart
		WebElement prod = products.stream()
				.filter(product -> product.findElement(By.cssSelector("b")).getText().equals("ADIDAS ORIGINAL"))
				.findFirst().orElse(null);
		prod.findElement(By.cssSelector(".card-body button:last-of-type")).click();

		// wait for product add to cart
		// wait to appear product add to cart text
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".toast-container")));// to appear prod
																										// add to cart
																										// text
		// wait for animation to end
		// wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".ng-animating")));
		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));

		// now go to cart
		driver.findElement(By.cssSelector("[routerlink*='cart']")).click();

		// check cart item are as selected
		List<WebElement> cartprods = driver.findElements(By.cssSelector(".cartSection h3"));
		// by java stream
		boolean match = cartprods.stream().anyMatch(cartprod -> cartprod.getText().equalsIgnoreCase(name));
		Assert.assertTrue(match);
//		//by java for loop
//		for (int i = 0; i < cartprods.size(); i++) {
//			String cartprod = cartprods.get(i).getText();
//			if (cartprod.equalsIgnoreCase(name)) {
//				System.out.println(cartprod +" is equals "+ name );
//				Assert.assertTrue(true);
//				break;
//			}
//		}

		driver.findElement(By.cssSelector(".totalRow button")).click(); // click checkout

		// select country from dropdown

		wait.until(
				ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector("[placeholder='Select Country']")));

		// driver.findElement(By.cssSelector("[placeholder='Select
		// Country']")).sendKeys(countryname);
		Actions a = new Actions(driver);
		a.sendKeys(driver.findElement(By.cssSelector("[placeholder='Select Country']")), countryname).build().perform();
		driver.findElement(By.xpath("(//button[contains(@class,'ta-item')])[2]")).click();
//		List<WebElement> countrylist = driver.findElements(By.xpath("(//button[contains(@class,'ta-item')])"));
//		for (int i = 0; i < countrylist.size(); i++) {
//			String country = countrylist.get(i).getText();
//			if (country.equalsIgnoreCase(countryname)) {
//				countrylist.get(i).click();
//				break;
//			}
//		}
		// to click on place order
		// wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("(//button[contains(@class,'ta-item')])")));

		WebElement tp = driver.findElement(By.cssSelector(".btnn.action__submit.ng-star-inserted"));
//	a.sendKeys(Keys.PAGE_DOWN);
//	tp.click();
		// wait.until(ExpectedConditions.visibilityOf(tp));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click()", tp);

		String confirmmsg = driver.findElement(By.cssSelector(".hero-primary")).getText();
		Assert.assertTrue(confirmmsg.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
		System.out.println("Your Transaction ID is "
				+ driver.findElement(By.xpath("//label[@class='ng-star-inserted']")).getText());
		driver.quit();
	}
}
