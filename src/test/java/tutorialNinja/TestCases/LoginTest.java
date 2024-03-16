package tutorialNinja.TestCases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.tutorialNinja.Utilities.Util;

import tutorialNinja.TestBase.TestBase;
import tutorialNinja.TestData.ExcelCode;

public class LoginTest extends TestBase {
	public LoginTest() throws Exception {
		super();
		
	}
	public WebDriver driver;
	
	@BeforeMethod
	public void loginSetup() {
		driver = initalizeBrowserAndOpenApplication(prop.getProperty("browser"));
		driver.findElement(By.linkText("My Account")).click();
		driver.findElement(By.linkText("Login")).click();
		
		
	}
	
	
	@Test( priority = 1, dataProvider = "TNLogin",dataProviderClass = ExcelCode.class)
	public void loginWithAllCredentials(String email,String password){
		driver.findElement(By.id("input-email")).sendKeys(email);
		driver.findElement(By.id("input-password")).sendKeys(password);
		driver.findElement(By.cssSelector("input.btn.btn-primary")).click();
		Assert.assertTrue(driver.findElement(By.linkText("Logout")).isDisplayed());
		
	}
	@Test(priority = 2)
	public void loginWithValidEmailAndInvalidPassword(){
		driver.findElement(By.id("input-email")).sendKeys(prop.getProperty("validEmail"));
		driver.findElement(By.id("input-password")).sendKeys(dataProp.getProperty("invalidPassword"));
		driver.findElement(By.cssSelector("input.btn.btn-primary")).click();
		String actualWarningMessage = driver.findElement(By.className("div.alert.alert-danger.alert-dismissible")).getText();
		String expectedWarningMessage = dataProp.getProperty("loginWarningMessage");
		Assert.assertTrue(actualWarningMessage.contains(expectedWarningMessage));
	

}
	@Test(priority = 3)
	public void loginWithInvalidEmailAndValidPassword(){
		driver.findElement(By.id("input-email")).sendKeys(Util.emailWithDateTimeStamp());
		driver.findElement(By.id("input-password")).sendKeys(prop.getProperty("validEmail"));
		driver.findElement(By.cssSelector("input.btn.btn-primary")).click();
		String actualWarningMessage = driver.findElement(By.cssSelector("div.alert.alert-danger.alert-dismissible")).getText();
		String expectedWarningMessage = dataProp.getProperty("loginWarningMessage");
		Assert.assertTrue(actualWarningMessage.contains(expectedWarningMessage));
	}
	@Test(priority = 4)
	public void loginWithInvalidEmailAndInvalidPassword(){
		driver.findElement(By.id("input-email")).sendKeys(Util.emailWithDateTimeStamp());
		driver.findElement(By.id("input-password")).sendKeys(dataProp.getProperty("invalidPassword"));
		driver.findElement(By.cssSelector("input.btn.btn-primary")).click();
		String actualWarningMessage = driver.findElement(By.cssSelector("div.alert.alert-danger.alert-dismissible")).getText();
		String expectedWarningMessage = dataProp.getProperty("loginWarningMessage");
		Assert.assertTrue(actualWarningMessage.contains(expectedWarningMessage));
		}
	
	@Test(priority = 5)
	public void loginWithNoCredentials(){
		driver.findElement(By.cssSelector("input.btn.btn-primary")).click();
		String actualWarningMessage = driver.findElement(By.cssSelector("div.alert.alert-danger.alert-dismissible")).getText();
		String expectedWarningMessage = dataProp.getProperty("loginWarningMessage");
		Assert.assertTrue(actualWarningMessage.contains(expectedWarningMessage));
	}
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
}
