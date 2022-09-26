package AutomationPracticeFramework.com.Ecommerce_framework.Page_Object;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import AutomationPracticeFramework.com.Ecommerce_framework.AbstractComponent.AbstractComponent;

public class CheckoutPage extends AbstractComponent {

	WebDriver driver;

	public CheckoutPage(WebDriver driver) {
		// initialization
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "(//button[contains(@class,'ta-item')])")
	List<WebElement> countrylists;
	@FindBy(css = ".btnn.action__submit.ng-star-inserted")
	WebElement placeorder;
	By selctCountryDD = By.cssSelector("[placeholder='Select Country']");

	public void selectCountryDD(String countryname) {
		waitforelementtoAppear(selctCountryDD);
//		Actions a = new Actions(driver);
//		a.sendKeys(driver.findElement(selctCountryDD), countryname).build().perform();
//		countrylist.click();
		 driver.findElement(selctCountryDD).sendKeys(countryname);
		 List<WebElement> countrylist = countrylists;
		for (int i = 0; i < countrylist.size(); i++) {
			String country = countrylist.get(i).getText();
			if (country.equalsIgnoreCase(countryname)) {
				countrylist.get(i).click();
				break;
			}
		}
	}

	public OrderConfirmationPage placeOrder() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click()", placeorder);
		OrderConfirmationPage orderConfirmationPage = new OrderConfirmationPage(driver);
		return orderConfirmationPage;
	}

}
