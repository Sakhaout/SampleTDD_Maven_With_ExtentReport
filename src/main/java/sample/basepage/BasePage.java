package sample.basepage;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import io.github.bonigarcia.wdm.WebDriverManager;
import sample.utilies.CommonFunction;


public class BasePage {
	//public static RemoteWebDriver remoteDriver = null;
	public static WebDriver driver = null; 
	private static Properties properties_obj;
	private static Logger log = LogManager.getLogger(BasePage.class.getName());
	public static int Page_Load_TimeOut = 50;
	public static int Implicitly_Wait = 50;

	public BasePage() {
		try {
			String properties_path = System.getProperty("user.dir") + "\\src\\main\\resources\\PropertiesFile\\config.properties";
			properties_obj = new Properties();
			FileInputStream file_input = new FileInputStream(properties_path);
			properties_obj.load(file_input);
		}
		catch(IOException ex) {
			ex.getStackTrace();
		}
	}

	public static void initialition(String browser) {
		log.info("  Launching Browser - "+browser);
		if(browser.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
			DesiredCapabilities cap = new DesiredCapabilities();
			cap.setPlatform(Platform.LINUX);
			cap.setCapability("browserName", "chrome");
			ChromeOptions cop = new ChromeOptions();
			cop.merge(cap);
			driver = new ChromeDriver(cop);
//			try {
//				driver = new RemoteWebDriver(new URL("http://54.205.134.109:4444/wd/hub"),cap);
//			} catch (MalformedURLException e) {
//				e.printStackTrace();
//			}
		}else if(browser.equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			DesiredCapabilities cap = new DesiredCapabilities();
			cap.setPlatform(Platform.WINDOWS);
			cap.setCapability("port", "5555");
			cap.setCapability("browserName", "firefox");
			cap.setCapability("version", "78.0.2");
			try {
				driver = new RemoteWebDriver(new URL("http://54.205.134.109:4444/wd/hub"),cap);
			} catch (MalformedURLException e) {
				e.printStackTrace();
			}
		}else if(browser.equalsIgnoreCase("IE")) {
			WebDriverManager.iedriver().setup();
			try {
				driver = new RemoteWebDriver(new URL("http://54.205.134.109:4444/wd/hub"), null);
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		driver.manage().window().maximize();  //Windows will maximize.
		driver.manage().deleteAllCookies();		//Delete all cookies.
		CommonFunction.pageLoadTimeout(Page_Load_TimeOut); //Wait untile page are fully loeaded.
		CommonFunction.implicitlyWait(Implicitly_Wait); //Wait until element is found, but wait until a time.
		log.info("  Application is opening \n");
		driver.get(properties_obj.getProperty("PCRS_URI")); //Lunch the url.		

	}


}
