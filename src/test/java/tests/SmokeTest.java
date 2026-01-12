package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import base.BaseTest;
import base.DriverManager;

public class SmokeTest extends BaseTest{
	
	@Test
	public void verifyAppLaunch() {
		String currentActivity =
                DriverManager.getDriver().currentActivity();

        System.out.println("the Current Activity ---> : " + currentActivity);

        Assert.assertTrue(currentActivity.contains("ApiDemos"),
                "App did not launch successfully");
	}

}
