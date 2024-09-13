package core.framework.utils;

import java.io.File;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ReportGenerator {

	private ReportGenerator() {
		throw new IllegalStateException("Utility class");
	}

	private static ExtentReports extentReports;

	private static final String directoryPath = CommonUtils.createDir(Configuration.get("reportDirectoryPath"));

	public static ExtentReports getInstance() {
		if (extentReports == null) {
			extentReports = new ExtentReports();
			extentReports.attachReporter(getReporter());;
		}
		return extentReports;
	}

	private static ExtentSparkReporter getReporter() {
		// TODO Auto-generated method stub
		ExtentSparkReporter reporter=new ExtentSparkReporter(new File(directoryPath,(CommonUtils.getTimeStamp()+"_"+Configuration.get("reportName"))).getAbsolutePath());
		reporter.config().setDocumentTitle("TestExecutionSummary");
		reporter.config().setTheme(Theme.STANDARD);
		reporter.config().setEncoding("UTF-8");
		return reporter;
	}
}