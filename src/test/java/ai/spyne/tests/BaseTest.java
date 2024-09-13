package ai.spyne.tests;

import java.io.File;
import java.time.Duration;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import core.framework.utils.CommonUtils;
import core.framework.utils.Driver;

public class BaseTest {

	protected WebDriver driver;
	protected WebDriverWait wait;

	protected String fileUploadPath = new File("src/test/resources/executableScript/fileUpload.Exe").getAbsolutePath();
	protected String imagePath = new File("src/test/resources/testFiles/download.jpg").getAbsolutePath();
	protected String invalidFilePath = new File("src/test/resources/testFiles/testFile.txt").getAbsolutePath();

	@BeforeTest
	@Parameters({ "browser" })
	public void setUp(String browser) {
		driver = Driver.getInstance(browser).getDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofMillis(60000));
		wait = new WebDriverWait(driver, Duration.ofMillis(30000));
	}

	@AfterTest
	public void tearDown() {
		Driver.quitBrowser();
	}

}
