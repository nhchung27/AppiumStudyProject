package api_learning;

import driver.DriverFactory;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;

public class TakingScreenShot {
    public static void main(String[] args) {
        DriverFactory.startAppiumServer();

        try {
            AppiumDriver<MobileElement> androidDriver = DriverFactory.getAndroidDriver();
            MobileElement loginLableElem = androidDriver.findElementByAccessibilityId("Login");
            loginLableElem.click();

            WebDriverWait wait = new WebDriverWait(androidDriver, 10L);
            wait.until(ExpectedConditions.presenceOfElementLocated(MobileBy.AccessibilityId("button-LOGIN")));

            

            //Taking screenshot | Whole screen
            File base64ScreenshotData = androidDriver.getScreenshotAs(OutputType.FILE);
            String fileLocation = System.getProperty("user.dir").concat("/screenshots/").concat("LoginForm.png");
            FileUtils.copyFile(base64ScreenshotData,new File(fileLocation));

            // Taking screenshoot on element
            MobileElement loginBtnElement = androidDriver.findElement(MobileBy.AccessibilityId("button-LOGIN"));
            File base64SLoginBtnData = loginBtnElement.getScreenshotAs(OutputType.FILE);
            String loginBtnDataLocation = System.getProperty("user.dir").concat("/screenshots/").concat("LoginButton.png");
            FileUtils.copyFile(base64SLoginBtnData,new File(loginBtnDataLocation));

        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
