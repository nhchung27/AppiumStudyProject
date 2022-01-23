package driver;

import caps.MobileCapabilityTypeEx;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.URL;
import java.util.concurrent.TimeUnit;


public class DriverFactoryEx {

    private AppiumDriver<MobileElement> appiumDriver;

    public AppiumDriver<MobileElement> getAndroidDriver(String udid, String systemPort) {
        initDriver(udid, systemPort);
        return appiumDriver;
    }
    public AppiumDriver<MobileElement> getAndroidDriver() {
        return appiumDriver;
    }

    private void initDriver(String udid, String systemPort) {
        try {

            DesiredCapabilities des = new DesiredCapabilities();
            des.setCapability(MobileCapabilityTypeEx.PLATFORM_NAME, "android");
            des.setCapability(MobileCapabilityTypeEx.AUTOMATION_NAME, "uiautomator2");
            des.setCapability(MobileCapabilityTypeEx.UDID, udid);
            des.setCapability(MobileCapabilityTypeEx.SYSTEM_PORT, systemPort);
            des.setCapability(MobileCapabilityTypeEx.APP_PACKAGE, "com.wdiodemoapp");
            des.setCapability(MobileCapabilityTypeEx.APP_ACTIVITY, "com.wdiodemoapp.MainActivity");

            URL remoteServer = new URL("http://localhost:4723/wd/hub");
            String hub = System.getProperty("hub") != null ? System.getProperty("hub") : System.getenv("hub");
            if (hub != null)
                remoteServer = new URL(hub.concat(":4444/wd/hub"));


//            URL server = new URL("http://localhost:4723/wd/hub");
//            URL server = new URL("http://192.168.1.104:4444/wd/hub");
//            appiumDriver = new AndroidDriver<>(server,des);
            appiumDriver = new AndroidDriver<>(remoteServer,des);
            appiumDriver.manage().timeouts().implicitlyWait(5L,TimeUnit.SECONDS);

        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void quitAppiumSession(){
        if (appiumDriver != null) {
            appiumDriver.quit();
            appiumDriver = null;
        }

    }
}
