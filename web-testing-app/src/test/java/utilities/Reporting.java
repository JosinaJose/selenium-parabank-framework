package utilities;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class Reporting extends TestListenerAdapter {
	public ExtentSparkReporter sparkReporter;
	public static ExtentReports extent;
	public ExtentTest test;

	// You can add extra system info here if needed
	private static final String SYSTEM_INFO = "Operating System: Windows 10, Browser: Chrome";

	public Reporting() {
		// Initialize ExtentReports once for all tests
		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
		String reportName = "Test-Report-" + timeStamp + ".html";

		// String reportPath = System.getProperty("user.dir") + File.separator +
		// "./Reports/ExtendReport" + File.separator + reportName;
		String reportPath = System.getProperty("user.dir") + File.separator + "Reports" + File.separator
				+ "ExtendReport" + File.separator + reportName;

		// Create and configure ExtentSparkReporter
		sparkReporter = new ExtentSparkReporter(reportPath);
		sparkReporter.config().setDocumentTitle("Automation Report");
		sparkReporter.config().setReportName("Functional Testing");
		sparkReporter.config().setTheme(Theme.DARK);

		// Initialize ExtentReports
		extent = new ExtentReports();
		extent.attachReporter(sparkReporter);

		// Set system info
		extent.setSystemInfo("Host Name", "localhost");
		extent.setSystemInfo("Environment", "QA");
		extent.setSystemInfo("User", "Josina");
		extent.setSystemInfo("Browser", "Chrome");
		extent.setSystemInfo("OS", SYSTEM_INFO);
	}

	@Override
	public void onStart(ITestContext testContext) {
		// Called before the test suite starts
		extent.createTest(testContext.getName());
	}

	@Override
	public void onTestStart(ITestResult result) {
		// Called when a test starts
		test = extent.createTest(result.getName());
		test.log(Status.INFO, "Test started: " + result.getName());
		// Optionally, add descriptions and other meta data
		test.info("Test Description: This test checks the login functionality.");
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		// Called when a test passes
		test.log(Status.PASS, "Test Case PASSED: " + result.getName());
		test.info("Execution Time: " + (result.getEndMillis() - result.getStartMillis()) / 1000 + " seconds");
	}

	@Override
	public void onTestFailure(ITestResult result) {
		// Called when a test fails
		test.log(Status.FAIL, "Test Case FAILED: " + result.getName());
		test.log(Status.FAIL, "Error: " + result.getThrowable());

		// Capture screenshot on failure (assuming you have a method to capture it)
		String screenshotPath = System.getProperty("user.dir") + File.separator + "Screenshots" + File.separator
				+ result.getName() + ".png";
		test.addScreenCaptureFromPath(screenshotPath);

		// Optionally, log environment and other contextual information
		test.info("Execution Time: " + (result.getEndMillis() - result.getStartMillis()) / 1000 + " seconds");
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		// Called when a test is skipped
		test.log(Status.SKIP, "Test Case SKIPPED: " + result.getName());
	}

	@Override
	public void onFinish(ITestContext testContext) {
		// Called after the test suite ends
		if (extent != null) {
			extent.flush();
		}
	}
}
