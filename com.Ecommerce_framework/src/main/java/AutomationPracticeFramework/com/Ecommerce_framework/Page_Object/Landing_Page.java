package AutomationPracticeFramework.com.Ecommerce_framework.Page_Object;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import AutomationPracticeFramework.com.Ecommerce_framework.AbstractComponent.AbstractComponent;

public class Landing_Page extends AbstractComponent {

	WebDriver driver;

	public Landing_Page(WebDriver driver) {
		// initialization
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	// WebElement useremail= driver.findElement(By.id("userEmail"));
// pagefactory

	@FindBy(id = "userEmail")
	WebElement useremail;
	@FindBy(id = "userPassword")
	WebElement userPassword;
	@FindBy(id = "login")
	WebElement submit;
	// div[@class='ng-tns-c4-10 ng-star-inserted ng-trigger ng-trigger-flyInOut
	// ngx-toastr toast-error']
	@FindBy(css = "[class*='flyInOut']")
	WebElement erroemsg;

	public ProductCatalog LoginApplication(String email, String password) {
		useremail.sendKeys(email);
		userPassword.sendKeys(password);
		submit.click();
		ProductCatalog productcatalog = new ProductCatalog(driver);
		return productcatalog;
	}

	public void goTo() {
		driver.get("https://rahulshettyacademy.com/client");// hit url
	}

	public String getErrormsg() {
		waitforwebelementtoAppear(erroemsg);
		return erroemsg.getText();
		
	}

}
