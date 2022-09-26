package AutomationPracticeFramework.com.Ecommerce_framework.AbstractComponent;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import AutomationPracticeFramework.com.Ecommerce_framework.Page_Object.CartPage;
import AutomationPracticeFramework.com.Ecommerce_framework.Page_Object.OrderPage;

public class AbstractComponent {

	WebDriver driver;

	public AbstractComponent(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	@FindBy(css = "[routerlink*='cart']")
	WebElement gotocart;
	
	@FindBy(css = "[routerlink*='myorders']")
	WebElement orderheader;

	public void waitforelementtoAppear(By Findby) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
		wait.until(ExpectedConditions.visibilityOfElementLocated(Findby));
	}
	public void waitforwebelementtoAppear(WebElement ele) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
		wait.until(ExpectedConditions.visibilityOfAllElements(ele));
	}

	public void waitForElementToDisAppear(WebElement ele) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
		wait.until(ExpectedConditions.invisibilityOf(ele));
	}

	public CartPage goToCart() {
		gotocart.click();
		CartPage cartPage = new CartPage(driver);
		return cartPage;
	}
	public OrderPage goToOrderPage() {
		orderheader.click();
		 OrderPage orderPage = new OrderPage(driver);
		return orderPage;
	}

}
