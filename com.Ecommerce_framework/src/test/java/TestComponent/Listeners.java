package TestComponent;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import AutomationPracticeFramework.com.Ecommerce_framework.resources.ExtentReportNG;

public class Listeners extends Testbase implements ITestListener{

	ExtentTest test;
	ExtentReports extent = ExtentReportNG.getExtentReportObject();
	ThreadLocal<ExtentTest> extentTest = new ThreadLocal<ExtentTest>();

	public void onTeststart(ITestResult result) {
	ExtentTest test = extent.createTest(result.getMethod().getMethodName());
	extentTest.set(test);
	}
	public void onTestSuccess(ITestResult result) {
		test.log(Status.PASS, "Test Pass !!!...");
		
	}
	public void onTestFailure(ITestResult result) {
		extentTest.get().fail(result.getThrowable());
		//ScreenShot
		try {
			driver = (WebDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String filepath = null;
		try {
			 filepath = getScreenShot(result.getMethod().getMethodName(),driver);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		extentTest.get().addScreenCaptureFromPath(filepath, result.getMethod().getMethodName());
	}
	public void onFinish(ITestContext context) {
		extent.flush();
	}
}
