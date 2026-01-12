package base;

import java.net.URL;
import java.time.Duration;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;

public class BaseTest {
	protected static Logger log = LogManager.getLogger(BaseTest.class);
	protected String udid;
	protected boolean isEmulator;


	@BeforeMethod
	@Parameters({"deviceName","udid","platformVersion","systemPort"})
	public void setUP(String deviceName,String udid,String platformVersion,String systemPort) throws Exception {

		this.udid=udid;
		this.isEmulator=udid.startsWith("emulator");

		String apkPath =System.getProperty("user.dir")+"\\ApiDemos-debug_1.apk";
		System.out.println("apkPath== "+apkPath);

		log.info("========================================");
		log.info("Starting test on device: {}", deviceName);
		log.info("UDID: {}", udid);
		log.info("APK Path: {}", apkPath);
		log.info("========================================");

		UiAutomator2Options options = new UiAutomator2Options();
		options.setPlatformName("Android");
		options.setDeviceName(deviceName);
		options.setUdid(udid);
		options.setPlatformVersion(platformVersion);
		options.setSystemPort(Integer.parseInt(systemPort));
		options.setAutomationName("UiAutomator2");

		options.setApp(apkPath);
		options.setNoReset(false);
		options.setFullReset(true);

		options.setIgnoreHiddenApiPolicyError(true);
		options.setUiautomator2ServerInstallTimeout(Duration.ofSeconds(60));
		options.setUiautomator2ServerLaunchTimeout(Duration.ofSeconds(60));
		options.setDisableSuppressAccessibilityService(true);
		URL appiumServerURL= new URL("http://127.0.0.1:4723");

		AndroidDriver driver = new AndroidDriver(appiumServerURL, options);
		DriverManager.setDriver(driver);
		log.info("Appium session created successfully");
	}

	@AfterMethod(alwaysRun=true)
	public void TearDown() {
		log.info("Tearing down session");

		DriverManager.quitDriver();
		try {
			DriverManager.quitDriver();
		} catch (Exception e) {
			log.warn("Driver quit issue: {}", e.getMessage());
		}

		if (isEmulator) {
			uninstallAppFromEmulator();
		}

		log.info("Session cleanup completed");

	}


	private void uninstallAppFromEmulator() {
		try {
	        log.info("Uninstalling ApiDemos from device: {}", udid);

	        ProcessBuilder pb = new ProcessBuilder( "adb", "-s",udid,"uninstall","io.appium.android.apis");
	        pb.redirectErrorStream(true);
	        Process process = pb.start();

	        int exitCode = process.waitFor();
	        log.info("ADB uninstall completed with exit code: {}", exitCode);

	    } catch (Exception e) {
	        log.warn("Uninstall failed for device {} : {}", udid, e.getMessage());
	    }}
}

