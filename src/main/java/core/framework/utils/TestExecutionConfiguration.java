package core.framework.utils;

import java.util.Collection;
import java.util.List;

import org.testng.ITestContext;
import org.testng.ITestNGMethod;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;
import org.testng.xml.XmlTest;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;

public class TestExecutionConfiguration extends TestListenerAdapter {

	private ExtentReports extent = ReportGenerator.getInstance();
	ExtentTest test;
	private ITestNGMethod iTestMethod;

	@Override
	public void onTestSuccess(ITestResult tr) {
		// TODO Auto-generated method stub
		iTestMethod = tr.getMethod();
		String testMethodName = iTestMethod.getMethodName();
		test = extent.createTest(testMethodName + "-" + iTestMethod.getDescription());
		test.log(Status.PASS, MarkupHelper.createLabel(testMethodName + "PASSED", ExtentColor.GREEN));
		super.onTestSuccess(tr);
	}

	@Override
	public void onTestFailure(ITestResult tr) {
		// TODO Auto-generated method stub
		super.onTestFailure(tr);
		iTestMethod = tr.getMethod();
		String testMethodName = iTestMethod.getMethodName();
		String path = Driver.takeScreenshot(testMethodName);
		test = extent.createTest(testMethodName + "-" + iTestMethod.getDescription());
		test.log(Status.FAIL, MarkupHelper.createLabel(testMethodName + " FAILED ", ExtentColor.RED));
		test.log(Status.FAIL, tr.getThrowable().getMessage());
		test.addScreenCaptureFromPath(path);
	}

	@Override
	public void onTestSkipped(ITestResult tr) {
		// TODO Auto-generated method stub
		super.onTestSkipped(tr);
		iTestMethod = tr.getMethod();
		String testMethodName = iTestMethod.getMethodName();
		test = extent.createTest(testMethodName + "-" + iTestMethod.getDescription());
		test.log(Status.SKIP, MarkupHelper.createLabel(testMethodName + "SKIPPED", ExtentColor.YELLOW));
		test.log(Status.SKIP, tr.getThrowable().getMessage());
	}

	@Override
	public void onTestFailedWithTimeout(ITestResult tr) {
		// TODO Auto-generated method stub
		super.onTestFailedWithTimeout(tr);
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult tr) {
		// TODO Auto-generated method stub
		super.onTestFailedButWithinSuccessPercentage(tr);

	}

	@Override
	protected ITestNGMethod[] getAllTestMethods() {
		// TODO Auto-generated method stub
		return super.getAllTestMethods();
	}

	@Override
	public void onStart(ITestContext testContext) {
		// TODO Auto-generated method stub
		super.onStart(testContext);
	}

	@Override
	public void onFinish(ITestContext testContext) {
		// TODO Auto-generated method stub
		super.onFinish(testContext);
		extent.flush();
	}

	@Override
	public List<ITestResult> getFailedButWithinSuccessPercentageTests() {
		// TODO Auto-generated method stub
		return super.getFailedButWithinSuccessPercentageTests();
	}

	@Override
	public List<ITestResult> getFailedTests() {
		// TODO Auto-generated method stub
		return super.getFailedTests();
	}

	@Override
	public List<ITestResult> getPassedTests() {
		// TODO Auto-generated method stub
		return super.getPassedTests();
	}

	@Override
	public List<ITestResult> getSkippedTests() {
		// TODO Auto-generated method stub
		return super.getSkippedTests();
	}

	@Override
	public Collection<ITestResult> getTimedoutTests() {
		// TODO Auto-generated method stub
		return super.getTimedoutTests();
	}

	@Override
	public void setAllTestMethods(List<ITestNGMethod> allTestMethods) {
		// TODO Auto-generated method stub
		super.setAllTestMethods(allTestMethods);
	}

	@Override
	public void setFailedButWithinSuccessPercentageTests(List<ITestResult> failedButWithinSuccessPercentageTests) {
		// TODO Auto-generated method stub
		super.setFailedButWithinSuccessPercentageTests(failedButWithinSuccessPercentageTests);
	}

	@Override
	public void setFailedTests(List<ITestResult> failedTests) {
		// TODO Auto-generated method stub
		super.setFailedTests(failedTests);
	}

	@Override
	public void setPassedTests(List<ITestResult> passedTests) {
		// TODO Auto-generated method stub
		super.setPassedTests(passedTests);
	}

	@Override
	public void setSkippedTests(List<ITestResult> skippedTests) {
		// TODO Auto-generated method stub
		super.setSkippedTests(skippedTests);
	}

	@Override
	public void onTestStart(ITestResult result) {
		// TODO Auto-generated method stub
		iTestMethod = result.getMethod();
		super.onTestStart(result);
	}

	@Override
	public List<ITestContext> getTestContexts() {
		// TODO Auto-generated method stub
		return super.getTestContexts();
	}

	@Override
	public List<ITestResult> getConfigurationFailures() {
		// TODO Auto-generated method stub
		return super.getConfigurationFailures();
	}

	@Override
	public void onConfigurationFailure(ITestResult itr) {
		// TODO Auto-generated method stub
		super.onConfigurationFailure(itr);
	}

	@Override
	public List<ITestResult> getConfigurationSkips() {
		// TODO Auto-generated method stub
		return super.getConfigurationSkips();
	}

	@Override
	public void beforeConfiguration(ITestResult tr) {
		// TODO Auto-generated method stub
		super.beforeConfiguration(tr);
	}

	@Override
	public void onConfigurationSkip(ITestResult itr) {
		// TODO Auto-generated method stub
		super.onConfigurationSkip(itr);
	}

	@Override
	public void onConfigurationSuccess(ITestResult itr) {
		// TODO Auto-generated method stub
		super.onConfigurationSuccess(itr);
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString();
	}

}