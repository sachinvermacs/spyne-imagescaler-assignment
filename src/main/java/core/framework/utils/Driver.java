package core.framework.utils;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.google.common.io.Files;

public class Driver {

	private static Driver driverInstance;

	public static ThreadLocal<WebDriver> driverThread = new ThreadLocal<>();

	private Driver() {
	}

	public void launchBrowser(String BrowserName) {
		if (BrowserName.equalsIgnoreCase("chrome")) {
			driverThread.set(new ChromeDriver());
			System.out.println("Chrome browser is launched");
		} else if (BrowserName.equalsIgnoreCase("firefox")) {
			driverThread.set(new FirefoxDriver());
			System.out.println("Firefox browser is launched");
		} else if (BrowserName.equalsIgnoreCase("edge")) {
			driverThread.set(new EdgeDriver());
			System.out.println("Edge browser is launched");
		} else {
			throw new IllegalArgumentException("Unexpected Value" + BrowserName);
		}
	}

	public static Driver getInstance(String browser) {
		if (driverInstance == null) {
			synchronized (Driver.class) {
				if (driverInstance == null) {
					driverInstance = new Driver();
				}
			}
		}
		if (driverThread.get() == null) {
			driverInstance.launchBrowser(browser);
		}
		return driverInstance;
	}

	public WebDriver getDriver() {
		return driverThread.get();
	}

	public static void quitBrowser() {
		if(driverThread.get()!=null) {
			driverThread.get().quit();
			driverThread.remove();
		}
	}
	
	public static String takeScreenshot(String methodName) {
		String dirPath = CommonUtils.createDir(Configuration.get("screenshotDirPath"));
		String destination = dirPath + "\\" + "Failed_" + methodName + "" + CommonUtils.getTimeStamp() + ".png";
		try {
			File scrFile = ((TakesScreenshot) driverThread.get()).getScreenshotAs(OutputType.FILE);
			Files.copy(scrFile, new File(destination));
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		return destination;
	}
}