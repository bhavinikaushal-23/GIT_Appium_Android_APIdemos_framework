package base;

import java.net.URL;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;

public class BaseTest {

	@BeforeMethod
	@Parameters({"deviceName","udid","platformVersion","systemPort"})
	public void setUP(String deviceName,String udid,String platformVersion,String systemPort) throws Exception {
		
		String apkPath =System.getProperty("user.dir")+"\\ApiDemos-debug_1.apk";
		System.out.println("apkPath== "+apkPath);


		UiAutomator2Options options = new UiAutomator2Options();
		options.setPlatformName("Android");
		options.setDeviceName(deviceName);
		options.setUdid(udid);
		options.setPlatformVersion(platformVersion);
		options.setSystemPort(Integer.parseInt(systemPort));
		
		options.setAutomationName("UiAutomator2");
		options.setCapability("ignoreHiddenApiPolicyError", true);
	
		 options.setCapability("ignoreHiddenApiPolicyError", true);
		 options.setCapability("noReset", true);
		 options.setCapability("fullReset", false);
		 options.setCapability("disableSuppressAccessibilityService", true);
		 options.setCapability("uiautomator2ServerLaunchTimeout", 60000);
		 options.setCapability("uiautomator2ServerInstallTimeout", 60000);
	
		options.setApp(apkPath);

		URL appiumServerURL= new URL("http://127.0.0.1:4723");

		AndroidDriver driver = new AndroidDriver(appiumServerURL, options);
		DriverManager.setDriver(driver);
	}

	@AfterMethod
	public void TearDown() {
		
			DriverManager.quitDriver();
		}
	}

