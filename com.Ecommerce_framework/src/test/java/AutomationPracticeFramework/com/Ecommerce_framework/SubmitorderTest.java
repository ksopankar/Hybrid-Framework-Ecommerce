package AutomationPracticeFramework.com.Ecommerce_framework;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import AutomationPracticeFramework.com.Ecommerce_framework.Page_Object.CartPage;
import AutomationPracticeFramework.com.Ecommerce_framework.Page_Object.CheckoutPage;
import AutomationPracticeFramework.com.Ecommerce_framework.Page_Object.OrderConfirmationPage;
import AutomationPracticeFramework.com.Ecommerce_framework.Page_Object.OrderPage;
import AutomationPracticeFramework.com.Ecommerce_framework.Page_Object.ProductCatalog;
import TestComponent.Testbase;

public class SubmitorderTest extends Testbase {

	String productname ;
	String countryname = "kenya";
	@Test(dataProvider = "getdata", groups={"purchase"})
	public void SubmitOrderTest(HashMap<String, String> input) throws IOException {

		

		// get login
		ProductCatalog productcatalog = landingPage.LoginApplication(input.get("email"), input.get("password"));

		// List<WebElement> products = productcatalog.getproductslist();
		// get item to cart
		productcatalog.addProductToCart(input.get("productcatalog"));

		// now go to cart
		CartPage cartPage = productcatalog.goToCart();

		// check cart item are as selected & click checkout
		boolean match = cartPage.checkProductInCart(input.get("productcatalog"));
		Assert.assertTrue(match);

		CheckoutPage orderPage = cartPage.checkout();

		// select country from dropdown
		orderPage.selectCountryDD(countryname);

		// to click on place order
		OrderConfirmationPage orderConfirmationPage = orderPage.placeOrder();

		// confirmation massage and trans ID
		String confirmmsg = orderConfirmationPage.PurchaseConfirmation_TransactionID();
		Assert.assertTrue(confirmmsg.equalsIgnoreCase("THANKYOU FOR THE ORDER."));

	}
	
	@Test(dependsOnMethods = {"SubmitOrderTest"})
	public void OrderHistoryTest() {
		ProductCatalog productcatalog = landingPage.LoginApplication("Kamlesh@gmail.com", "Kam12345");
		OrderPage orderpage = productcatalog.goToOrderPage();
		orderpage.VerifyOrderDisplay(productname);}
	
	public String getScreenShot(String testcasename) throws IOException {
		
		TakesScreenshot ts = (TakesScreenshot)driver;
		File Source = ts.getScreenshotAs(OutputType.FILE);
		File Target = new File(System.getProperty("user.dir")+"//Screenshot//"+ testcasename+".png");
		FileUtils.copyFile(Source, Target);
		return System.getProperty("user.dir")+"//Screenshot//"+ testcasename+".png"; 
	} 
	
		
		@DataProvider
		public Object[][] getdata() throws IOException
		{
			
			List<HashMap<String, String>> data = getDataJsonToMap(System.getProperty("user.dir")+"\\src\\test\\java\\Data\\PurchaseOrder.json");
//			HashMap<String, String> map = new HashMap<String , String >();
//			map.put("email", "Kamlesh@gmail.com");
//			map.put("passwprd", "Kaml12345");
//			map.put("productname", "zara coat 3");
//			HashMap<String, String> map1 = new HashMap<String , String >();
//			map1.put("email", "anshika@gmail.com");
//			map1.put("passwprd", "Iamking@000");
//			map1.put("productname", "iphone 13 pro");
//			return new Object[][] {{map1}, {map}};
			return new Object[][] {{data.get(0)}, {data.get(1)}};
			}
		
		
		
	}
