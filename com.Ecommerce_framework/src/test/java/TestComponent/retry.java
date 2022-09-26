package TestComponent;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class retry implements IRetryAnalyzer {

	@Override
	public boolean retry(ITestResult result) {
		int count = 0;
		int maxtry = 1;
		if (count > maxtry) {
			count++;
			return true;
		}
		return false;
	}

}
 