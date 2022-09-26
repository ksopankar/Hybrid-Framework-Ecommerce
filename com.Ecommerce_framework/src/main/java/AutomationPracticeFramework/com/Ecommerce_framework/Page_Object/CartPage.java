package AutomationPracticeFramework.com.Ecommerce_framework.Page_Object;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import AutomationPracticeFramework.com.Ecommerce_framework.AbstractComponent.AbstractComponent;

public class CartPage extends AbstractComponent {

	WebDriver driver;

	public CartPage(WebDriver driver) {
		// initialization
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css = ".cartSection h3")
	List<WebElement> productincart;
	@FindBy(css = ".totalRow button")
	WebElement checkout;
	
	public boolean checkProductInCart(String productname) {
		List<WebElement> cartprods = productincart;
		// by java stream
		boolean match = cartprods.stream().anyMatch(cartprod -> cartprod.getText().equalsIgnoreCase(productname));
		return match;
//		//by java for loop
//		for (int i = 0; i < cartprods.size(); i++) {
//			String cartprod = cartprods.get(i).getText();
//			if (cartprod.equalsIgnoreCase(productname)) {
//				System.out.println(cartprod +" is equals "+ productname );
//				Assert.assertTrue(true);
//				break;
//			}
//		}
	}
	
	public CheckoutPage checkout() {
		checkout.click();
		CheckoutPage orderPage = new CheckoutPage(driver);
		return orderPage;
	}

}
