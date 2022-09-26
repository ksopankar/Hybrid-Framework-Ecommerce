package TestComponent;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import AutomationPracticeFramework.com.Ecommerce_framework.Page_Object.Landing_Page;
import io.github.bonigarcia.wdm.WebDriverManager;

public class Testbase {
	public WebDriver driver;
	public Landing_Page landingPage;

	public WebDriver InitializeDriver() throws IOException {

		// properties setup
		Properties prop = new Properties();

		FileInputStream fil = new FileInputStream(System.getProperty("user.dir")
				+ "\\src\\main\\java\\AutomationPracticeFramework\\com\\Ecommerce_framework\\resources\\resource.properties");

		prop.load(fil);
		String browsername = prop.getProperty("browser");

		if (browsername.equalsIgnoreCase("chrome")) {
			// System.getProperty("webdriver.chrome.driver", "./Drivers/chromedriver.exe");

			WebDriverManager.chromedriver().setup();

			driver = new ChromeDriver();
		} else if (browsername.equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		} else if (browsername.equalsIgnoreCase("edge")) {
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
	}
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
			driver.manage().window().maximize();
			return driver;
		}
	

	public List<HashMap<String, String>> getDataJsonToMap(String filepath) throws IOException {
		// read json to string
		String JsonsContents = FileUtils.readFileToString(new File(filepath), StandardCharsets.UTF_8);

		// String toHashMAp JacksonDataBind
		ObjectMapper mapper = new ObjectMapper();
		List<HashMap<String, String>> data = mapper.readValue(JsonsContents,
				new TypeReference<List<HashMap<String, String>>>() {
				});
		return data;

	}

	public String getScreenShot(String testcasename, WebDriver driver) throws IOException {

		TakesScreenshot ts = (TakesScreenshot) driver;
		File Source = ts.getScreenshotAs(OutputType.FILE);
		File Target = new File(System.getProperty("user.dir") + "//Screenshot//" + testcasename + ".png");
		FileUtils.copyFile(Source, Target);
		return System.getProperty("user.dir") + "//Screenshot//" + testcasename + ".png";
	}

	@BeforeMethod(alwaysRun = true)
	public Landing_Page LouchApplication() throws IOException {
		driver = InitializeDriver();
		landingPage = new Landing_Page(driver);
		landingPage.goTo();// hit url
		return landingPage;
	}

	@AfterMethod(alwaysRun = true)
	public void tearDown() {
		driver.close();
	}

//	public void onTeststart(ITestResult result) {
//		// TODO Auto-generated method stub
//
//	}
}