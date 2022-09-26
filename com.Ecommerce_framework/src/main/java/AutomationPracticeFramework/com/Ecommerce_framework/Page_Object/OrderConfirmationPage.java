package AutomationPracticeFramework.com.Ecommerce_framework.Page_Object;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import AutomationPracticeFramework.com.Ecommerce_framework.AbstractComponent.AbstractComponent;

public class OrderConfirmationPage extends AbstractComponent {

	WebDriver driver;

	public OrderConfirmationPage(WebDriver driver) {
		// initialization
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = ".hero-primary")
	WebElement confrmationMsg;
	@FindBy(xpath = "//label[@class='ng-star-inserted']")
	WebElement TransID;

	public String PurchaseConfirmation_TransactionID() {
		String confirmmsg = confrmationMsg.getText();
		System.out.println("Your Transaction ID is " + TransID.getText());
		return confirmmsg;
	}

}
