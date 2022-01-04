package api_learning;

import driver.DriverFactory;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;

public class FormHandling {
    public static void main(String[] args) {
        DriverFactory.startAppiumServer();
        AndroidDriver<MobileElement> androidDriver = DriverFactory.getAndroidDriver();

        try {
            //Click on Login lable
            MobileElement loginLableElem = androidDriver.findElementByAccessibilityId("Login");
            loginLableElem.click();

           //Finding Elements
            MobileElement usernameElem = androidDriver.findElementByAccessibilityId("input-email");
            MobileElement passwordElem = androidDriver.findElementByAccessibilityId("input-password");
            MobileElement loginBtnElem = androidDriver.findElementByAccessibilityId("button-LOGIN");


            //Input username
            usernameElem.sendKeys("nhchung");

            // Input password
            passwordElem.sendKeys("12345678");

            //Click on Login button
            loginBtnElem.click();

            //clear
            usernameElem.clear();
            usernameElem.sendKeys("nhchung@fossil.com");

            loginBtnElem.click();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            androidDriver.quit();
        }

    }
}
