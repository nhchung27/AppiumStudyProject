package api_learning;

import caps.MobileCapabilityTypeEx;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.URL;
import java.util.concurrent.TimeUnit;

public class LaunchingApp {
    public static void main(String[] args) {
        try {
            AppiumDriver<MobileElement> appiumDriver = null;


            DesiredCapabilities des = new DesiredCapabilities();
            des.setCapability(MobileCapabilityTypeEx.PLATFORM_NAME, "android");
            des.setCapability(MobileCapabilityTypeEx.AUTOMATION_NAME, "uiautomator2");
            des.setCapability(MobileCapabilityTypeEx.UDID, "09261FDD4003CH");
            des.setCapability(MobileCapabilityTypeEx.APP_PACKAGE, "com.wdiodemoapp");
            des.setCapability(MobileCapabilityTypeEx.APP_ACTIVITY, "com.wdiodemoapp.MainActivity");

            URL server = new URL("http://localhost:4723/wd/hub");
            appiumDriver = new AndroidDriver<MobileElement>(server,des);
            if (appiumDriver != null) {
                appiumDriver.manage().timeouts().implicitlyWait(30L, TimeUnit.SECONDS);
                System.out.println("Connected to appium server and launched target app!");
            } else {
                System.out.println("Error when connecting with Appium server!");
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
        }


}
