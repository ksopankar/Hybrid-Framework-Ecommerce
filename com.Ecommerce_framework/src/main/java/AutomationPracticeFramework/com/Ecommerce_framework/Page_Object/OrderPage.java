package AutomationPracticeFramework.com.Ecommerce_framework.Page_Object;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import AutomationPracticeFramework.com.Ecommerce_framework.AbstractComponent.AbstractComponent;

public class OrderPage extends AbstractComponent {
	WebDriver driver;

	public OrderPage(WebDriver driver) {
		// initialization
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = "tr td:nth-child(3)")
	List<WebElement> orderincart;

	@FindBy(css = ".totalRow button")
	WebElement checkout;

	public boolean VerifyOrderDisplay(String productname) {
		List<WebElement> orderedProd = orderincart;
		// by java stream
		boolean match = orderedProd.stream().anyMatch(cartprod -> cartprod.getText().equalsIgnoreCase(productname));
		return match;
	}
}
