package sample.basepage;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;


import sample.utilies.CommonFunction;


public class BasePage {
	public static WebDriver driver = null; 
	public static Properties properties_obj;
	private static Logger log = LogManager.getLogger(BasePage.class.getName());
	public static int Page_Load_TimeOut = 50;
	public static int Implicitly_Wait = 50;
	private static String chromeDriver = "\\src\\main\\resources\\Driver\\chromedriver.exe";
	private static String geckoDriver = "\\src\\main\\resources\\Driver\\geckodriver.exe";
	private static String ieDriver = "\\src\\main\\resources\\Driver\\IEDriverServer.exe";
	
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
		//String browser = properties_obj.getProperty("browser"); //Get properties from properties file.
		String path_chromeDriver = System.getProperty("user.dir") + chromeDriver;
		String path_gekoDriiver = System.getProperty("user.dir") + geckoDriver;
		String path_IEDriver = System.getProperty("user.dir") + ieDriver;
		log.info("  Launching Browser - "+browser);
		if(browser.equalsIgnoreCase("chrome")) {
			System.setProperty("webdriver.chrome.driver", path_chromeDriver);
			driver = new ChromeDriver();
		}else if(browser.equalsIgnoreCase("firefox")) {
			System.setProperty("webdriver.gecko.driver", path_gekoDriiver);
			driver = new FirefoxDriver();
		}else if(browser.equalsIgnoreCase("IE")) {
			// Set desired capabilities to Ignore IEDriver IGNORING_SECURITY_DOMAINS.
			DesiredCapabilities capabilities = DesiredCapabilities.internetExplorer();
			capabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
			
			// Set desired capabilities to Ignore IEDriver zoom level settings and disable native events.
			capabilities.setCapability("EnableNativeEvents", false);
			capabilities.setCapability("ignoreZoomSetting", true);
			System.setProperty("webdriver.ie.driver", path_IEDriver);
			driver = new InternetExplorerDriver(capabilities);
		}
		
		driver.manage().window().maximize();  //Windows will maximize.
		driver.manage().deleteAllCookies();		//Delete all cookies.
		CommonFunction.pageLoadTimeout(Page_Load_TimeOut); //Wait untile page are fully loeaded.
		CommonFunction.implicitlyWait(Implicitly_Wait); //Wait until element is found, but wait until a time.
		log.info("  Application is opening \n");
		driver.get(properties_obj.getProperty("PCRS_URI")); //Lunch the url.		
		
	}


}
