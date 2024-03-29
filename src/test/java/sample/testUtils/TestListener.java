package sample.testUtils;

import com.relevantcodes.extentreports.LogStatus;

import sample.basepage.BasePage;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestListener extends BasePage implements ITestListener {
	
    private static String getTestMethodName(ITestResult iTestResult) {
        return iTestResult.getMethod().getConstructorOrMethod().getName();
    }
 
    @Override
    public void onStart(ITestContext iTestContext) {
        System.out.println("\n"+iTestContext.getName() + " is START");
        iTestContext.setAttribute("WebDriver", this.driver);
        System.out.println("------------------------------------------");
    }
 
    @Override
    public void onFinish(ITestContext iTestContext) {
        System.out.println(iTestContext.getName() + " is FINISHED\n");
        //Do tier down operations for extentreports reporting!
        ExtentTestManager.endTest();
        ExtentManager.getReporter().flush();
    }
 
    @Override
    public void onTestStart(ITestResult iTestResult) {
        System.out.println("Test Case: " + getTestMethodName(iTestResult) + " - START");
    }
 
    @Override
    public void onTestSuccess(ITestResult iTestResult) {
        System.out.println("Test Case: " + getTestMethodName(iTestResult) + " - SUCCEED");
        //ExtentReports log operation for passed tests.
        ExtentTestManager.getTest().log(LogStatus.PASS, "Test passed");
    }
 
    @Override
    public void onTestFailure(ITestResult iTestResult) {
        System.out.println("Test Case: " + getTestMethodName(iTestResult) + " - FAILED");
 
        //Get driver from BaseTest and assign to local webDriver variable.
        Object testClass = iTestResult.getInstance();
        WebDriver webDriver = ((BasePage) testClass).driver;
 
        //Take base64Screenshot screenshot.
        String base64Screenshot = "data:image/png;base64," + ((TakesScreenshot) webDriver).
            getScreenshotAs(OutputType.BASE64);
 
        //ExtentReports log and screenshot operations for failed tests.
        ExtentTestManager.getTest().log(LogStatus.FAIL, "Test Failed",
            ExtentTestManager.getTest().addBase64ScreenShot(base64Screenshot));
    }
 
    @Override
    public void onTestSkipped(ITestResult iTestResult) {
        System.out.println("Test Case: " + getTestMethodName(iTestResult) + " - SKIPPED");
        //ExtentReports log operation for skipped tests.
        ExtentTestManager.getTest().log(LogStatus.SKIP, "Test Skipped");
    }
 
    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {
        System.out.println("Test failed but it is in defined success ratio " + getTestMethodName(iTestResult));
    }
}
