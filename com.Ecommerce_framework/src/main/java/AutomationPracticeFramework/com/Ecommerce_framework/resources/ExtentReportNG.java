package AutomationPracticeFramework.com.Ecommerce_framework.resources;

import org.testng.annotations.BeforeTest;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReportNG {
	
	public static ExtentReports getExtentReportObject() {

		com.aventstack.extentreports.ExtentReports extent;

		String path = System.getProperty("user.dir") + "\\reports\\index.html";
		ExtentSparkReporter reporter = new ExtentSparkReporter(path);
		reporter.config().setReportName("Web Automation Report");
		reporter.config().setDocumentTitle("Test Results");

		extent = new com.aventstack.extentreports.ExtentReports();
		extent.attachReporter(reporter);
		extent.setSystemInfo("Test Engineer", "Kamlesh");
		return extent;
	}

}
