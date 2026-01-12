package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import base.BaseTest;
import base.DriverManager;

public class SmokeTest extends BaseTest{
	
	@Test
	public void verifyAppLaunch() {
		log.info("___________Launching ApiDemos app____________");
		String currentActivity = DriverManager.getDriver().currentActivity();

      log.info("the Current Activity ---> : " + currentActivity);

        Assert.assertTrue(currentActivity.contains("ApiDemos"),
                "App did not launch successfully");
	}

}
