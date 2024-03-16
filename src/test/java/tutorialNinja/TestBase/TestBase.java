package tutorialNinja.TestBase;

import java.io.FileInputStream;
import java.time.Duration;
import java.util.Arrays;
import java.util.Properties;

import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.tutorialNinja.Utilities.Util;

public class TestBase {
	public WebDriver driver;
	public Properties prop;
	public FileInputStream ip;
	public ChromeOptions options;
	public Properties dataProp;
	
	
	public TestBase() throws Exception {
		
		prop = new Properties();
		ip = new FileInputStream(System.getProperty("user.dir") + "\\src\\main\\java\\com\\tutorialNinja\\Config\\Config.properties");
		prop.load(ip);
		
		dataProp = new Properties();
		ip = new FileInputStream(System.getProperty("user.dir") + "\\src\\test\\java\\tutorialNinja\\TestData\\testData.properties" );
		dataProp.load(ip);
	}
	
	public WebDriver initalizeBrowserAndOpenApplication(String browserName) {
		if(browserName.equals("chrome")) {
			options = new ChromeOptions();
			options.setPageLoadStrategy(PageLoadStrategy.EAGER);
			options.addArguments("--start-maximazed");
			options.addArguments("--incognito");
			options.setExperimentalOption("excludeSwitches", Arrays.asList("enable-automation","disable-infobars"));
			driver = new ChromeDriver(options);
		}else if(browserName.equals("fireFox")) {
			driver = new FirefoxDriver();
			driver.manage().window().maximize();
		}else if(browserName.equals("Edge")) {
			driver = new EdgeDriver();
		    driver.manage().window().maximize();
		
		}
		
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Util.IMPLICT_WAIT_TIME));
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(Util.PAGELOAD_TIME_WAIT));
		driver.manage().timeouts().scriptTimeout(Duration.ofSeconds(Util.SCRIPT_TIME_WAIT));
		driver.get(prop.getProperty("url"));
	
		return driver;
		
		
	}
	

}
