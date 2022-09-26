package AutomationPracticeFramework.com.Ecommerce_framework;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import AutomationPracticeFramework.com.Ecommerce_framework.Page_Object.CartPage;
import AutomationPracticeFramework.com.Ecommerce_framework.Page_Object.ProductCatalog;
import TestComponent.Testbase;
import TestComponent.retry;

public class ErrorValidation extends Testbase {

	@Test(groups = {"ErrorValidation"}, retryAnalyzer = retry.class)
	public void LoginErrorValidation() throws IOException {

		String productname = "IPHONE 13 PRO";
		String countryname = "kenya";

		// get login
		landingPage.LoginApplication("Kamlesh@gmail.com", "Kam12345s");
		Assert.assertEquals("Incorrect email or password.", landingPage.getErrormsg());
		// div[@aria-label='Incorrect email or password.']
	}
	
		@Test
		public void errorValidationProduct() throws IOException{

			String productname = "IPHONE 133 PRO";
			String countryname = "kenya";
			
			// get login
			ProductCatalog productcatalog =  landingPage.LoginApplication("Kamlesh@gmail.com", "Kam12345");

			 List<WebElement> products = productcatalog.getproductslist();
			// get item to cart
			productcatalog.addProductToCart(productname);

			// now go to cart
			CartPage cartPage = productcatalog.goToCart();

			// check cart item are as selected & click checkout
			boolean match = cartPage.checkProductInCart(productname);
			Assert.assertTrue(match);
			
		}
}
