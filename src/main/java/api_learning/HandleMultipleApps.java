package api_learning;

import driver.DriverFactory;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.Activity;
import io.appium.java_client.android.AndroidDriver;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class HandleMultipleApps {
    public static void main(String[] args) {
        DriverFactory.startAppiumServer();
        AndroidDriver<MobileElement> androidDriver = DriverFactory.getAndroidDriver();
        try {
            // //*[@text=


            // Go to login form -> Input correct Credential to login
            MobileElement loginLableElem = androidDriver.findElementByAccessibilityId("Login");
            loginLableElem.click();
            MobileElement usernameElem = androidDriver.findElementByAccessibilityId("input-email");
            MobileElement passwordElem = androidDriver.findElementByAccessibilityId("input-password");
            MobileElement loginBtnElem = androidDriver.findElementByAccessibilityId("button-LOGIN");
            usernameElem.sendKeys("nhchung@fossil.com");
            passwordElem.sendKeys("12345678");
            loginBtnElem.click();

            // Put app into background
            androidDriver.runAppInBackground(Duration.ofSeconds(-1));

            // Open Setings  -> Handle Wifi
//            androidDriver.activateApp("com.android.settings");
//            androidDriver.findElementByXPath("//*[@text='Wi-Fi']").click();
            androidDriver.startActivity(new Activity("com.android.settings","com.android.settings.Settings$WifiSettings2Activity"));
            MobileElement wifiSwitchButtonElem = androidDriver.findElement(By.id("com.android.settings:id/switch_widget"));
            wifiSwitchButtonElem.click();
            if (wifiSwitchButtonElem.getText().equals("true"))
                wifiSwitchButtonElem.click();
//            else
//                wifiSwitchButtonElem.click();

//            List<MobileElement> matchedElems = androidDriver.findElementsByXPath("//*[@text='You found me!!!']");
//            boolean isWifiOn = wifiSwitchButtonElem.getText().equals("ON");
//
//            if (isWifiOn){
//                // OFF Wifi
//                wifiSwitchButtonElem.click();
//                // ON Wifi again
//                wifiSwitchButtonElem.click();
//            } else  {
//                wifiSwitchButtonElem.click();
//            }

            // Open the test app again
            androidDriver.terminateApp("com.android.settings");
            androidDriver.startActivity(new Activity("com.wdiodemoapp","com.wdiodemoapp.MainActivity"));


        } catch (Exception e) {
            e.printStackTrace();
        } finally {
//            androidDriver.quit();
        }

    }
}
