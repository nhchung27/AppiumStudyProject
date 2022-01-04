package driver;

import caps.MobileCapabilityTypeEx;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.URL;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class DriverFactory {
    private static AppiumDriverLocalService appiumServer;
    private static AndroidDriver<MobileElement> androidDriver;

    public static void startAppiumServer(){
        AppiumServiceBuilder appiumServiceBuilder = new AppiumServiceBuilder();
//        appiumServiceBuilder.withIPAddress("127.0.0.1").usingPort(4723);
        appiumServiceBuilder.withIPAddress("127.0.0.1").usingAnyFreePort();
        appiumServer = AppiumDriverLocalService.buildService(appiumServiceBuilder);
        appiumServer.start();
    }

    public static void stopAppiumServer(){
//        appiumServer.stop();
        String killNodeWindowsCommand = "taskkill /F /IM node.exe";
        String killNodeLinuxCommand = "killall node";
        String killNodeCmd = System.getProperty("os.name").toLowerCase().startsWith("windows")
                ? killNodeWindowsCommand : killNodeLinuxCommand;
        Runtime runtime = Runtime.getRuntime();
        try {
            String appiumPort = String.valueOf(appiumServer.getUrl().getPort());
            runtime.exec("kill -9 $(lsof -ti:" + appiumPort + ")");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static AndroidDriver<MobileElement> getAndroidDriver(){
        initAndroidDriver();
        return androidDriver;
    }

    public static void initAndroidDriver(){
        AppiumDriver<MobileElement> appiumDriver = null;


        DesiredCapabilities des = new DesiredCapabilities();
        des.setCapability(MobileCapabilityTypeEx.PLATFORM_NAME, "android");
        des.setCapability(MobileCapabilityTypeEx.AUTOMATION_NAME, "uiautomator2");
        des.setCapability(MobileCapabilityTypeEx.UDID, "09261FDD4003CH");
        des.setCapability(MobileCapabilityTypeEx.APP_PACKAGE, "com.wdiodemoapp");
        des.setCapability(MobileCapabilityTypeEx.APP_ACTIVITY, "com.wdiodemoapp.MainActivity");

        androidDriver = new AndroidDriver<MobileElement>(appiumServer.getUrl(),des);
        androidDriver.manage().timeouts().implicitlyWait(3L, TimeUnit.SECONDS); // cách nhau 500 miliseconds giữa các lần

//        if (appiumDriver != null) {
//            appiumDriver.manage().timeouts().implicitlyWait(30L, TimeUnit.SECONDS);
//            System.out.println("Connected to appium server and launched target app!");
//        } else {
//            System.out.println("Error when connecting with Appium server!");
//        }
    }
}
