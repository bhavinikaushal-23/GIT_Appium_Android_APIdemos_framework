package base;

import io.appium.java_client.android.AndroidDriver;

public class DriverManager {
	
	private static ThreadLocal<AndroidDriver> driver = new ThreadLocal<>();

    // returns AndroidDriver (NOT ThreadLocal)
    public static AndroidDriver getDriver() {
        return driver.get();
    }

    // accepts AndroidDriver
    public static void setDriver(AndroidDriver driverInstance) {
        driver.set(driverInstance);
    }

    public static void quitDriver() {
        if (driver.get() != null) {
            driver.get().quit();
            driver.remove();
        }
    }

}
