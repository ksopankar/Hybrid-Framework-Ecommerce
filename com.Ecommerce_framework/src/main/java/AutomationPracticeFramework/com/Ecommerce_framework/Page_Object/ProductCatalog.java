package AutomationPracticeFramework.com.Ecommerce_framework.Page_Object;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


import AutomationPracticeFramework.com.Ecommerce_framework.AbstractComponent.AbstractComponent;

public class ProductCatalog extends AbstractComponent {

	WebDriver driver;

	public ProductCatalog(WebDriver driver) {
		// initialization
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = ".mb-3")
	List<WebElement> products;
	@FindBy(css = ".ng-animating")
	WebElement spinner;
	@FindBy(css = "[routerlink*='cart']")
	WebElement gotocart;

	

	By productsby = By.cssSelector(".mb-3");
	By addtocart = By.cssSelector(".card-body button:last-of-type");
	By toast = By.cssSelector(".toast-container");

	// List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"))

	public List<WebElement> getproductslist() {
		waitforelementtoAppear(productsby);
		return products;
	}

	public WebElement getproductbyname(String productname) {
		// By java streams get item to cart
		WebElement prod = products.stream()
				.filter(product -> product.findElement(By.cssSelector("b")).getText().equals(productname)).findFirst()
				.orElse(null);
//		for (int i = 0; i < products.size(); i++) {
//			String prod = products.get(i).findElement(By.cssSelector("b")).getText();
//			if (prod.contains(productname)) {
//				driver.findElements(By.cssSelector(".card-body button:last-of-type")).get(i).click();
//			}
//		}
		return prod;
	}

	public void addProductToCart(String productname) {
		WebElement prod = getproductbyname(productname);
		prod.findElement(addtocart).click();
		// wait to appear product add to cart text
		waitforelementtoAppear(toast); // to appear prod add to cart text
		waitForElementToDisAppear(spinner);// to disappear spinner
	}

	

}
