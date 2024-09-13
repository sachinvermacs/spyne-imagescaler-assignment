package ai.spyne.tests;

import java.io.IOException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;

import core.framework.utils.Configuration;

public class Tests extends BaseTest {

	@Test(description = "Navigation", priority = 0)
	public void test01() {
		driver.get(Configuration.get("url"));
		Assert.assertEquals(driver.getCurrentUrl(), "https://www.spyne.ai/image-upscaler");
	}

	@Test(description = "File Upload", enabled = true, priority = 1)
	public void test02() throws IOException, Exception {
		wait.until(
				ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[contains(text(),'Upload an image')]")))
				.click();
		System.out.println(fileUploadPath);
		System.out.println(imagePath);
		Thread.sleep(3000);
		String[] cmd = { fileUploadPath, imagePath };
		Runtime.getRuntime().exec(cmd);
		By uploadedImage = By.xpath("//*[@id=\"__next\"]/div[2]/div[2]/div/div[2]/div/div[2]/div/div[2]/img");
		Assert.assertTrue(wait.until(ExpectedConditions.presenceOfElementLocated(uploadedImage)).isDisplayed());
	}

	@Test(enabled = true, description = "Image Upscaling", priority = 2)
	public void test03() throws Exception {
		Thread.sleep(2000);
		WebElement element4X = driver.findElement(By.xpath("//*[@id='__next']//button[2]//span[@class='text-xs']"));
		element4X.click();
		Assert.assertEquals(element4X.getText(),
				driver.findElement(By.xpath("//*[@id='__next']//button[3]//span")).getText());

		WebElement element2X = driver.findElement(By.xpath("//*[@id='__next']//button[1]//span[@class='text-xs']"));
		element2X.click();
		Assert.assertEquals(element2X.getText(),
				driver.findElement(By.xpath("//*[@id='__next']//button[3]//span")).getText());
	}

	@Test(enabled = true, description = "Invalid File Upload", priority = 3, expectedExceptions = {
			org.openqa.selenium.TimeoutException.class })
	public void test04() throws Exception {
		driver.navigate().to("https://www.spyne.ai/image-upscaler/upload");
		By uploadBtn = By.xpath("//*[contains(text(),'Upload')]");
		new Actions(driver).click(driver.findElement(uploadBtn)).build().perform();
		String[] cmd = { fileUploadPath, invalidFilePath };
		Runtime.getRuntime().exec(cmd);
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[contains(@class,'spinner')]")));
	}

	/*
	 * Some of UI validations are already done in above test cases
	 * 
	 * @Test(description = "UI Validation") public void test05() {
	 * driver.findElement(null); }
	 * 
	 * 
	 * Can not be tested as download button is not visible.
	 * 
	 * @Test(description = "Download Functionality") public void test06() {
	 * driver.findElement(null); }
	 */

}
