package resources;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class BaseDriver {
	public static Logger log = LogManager.getLogger(BaseDriver.class.getName());

	public static WebDriver driver;
	public String baseurl;
	public String homepageurl;

	@BeforeTest
	public WebDriver initializeDriver() throws IOException, FileNotFoundException {
		System.setProperty("webdriver.chrome.driver", "/usr/bin/chromedriver");
		try {
			ChromeOptions options = new ChromeOptions();
			options.addArguments(Arrays.asList("--no-sandbox", "--ignore-certificate-errors", "--homepage=about:blank",
					"--no-first-run"));
			options.addArguments("disable-infobars", "headless");
			options.setCapability(ChromeOptions.CAPABILITY, options);
			options.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
			driver = new ChromeDriver(options);
			System.out.println("options are : " + options);
		}
		
		catch (Exception e) {

			throw new Error(e);

		}
		
		return driver;
	}
	
	@Test
	public void getDriver() {
		driver.manage().window().maximize();
	}
}
