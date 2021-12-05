package caps;

import io.appium.java_client.remote.MobileCapabilityType;

public interface MobileCapabilityTypeEx extends MobileCapabilityType {
    String PLATFORM_VERSION = "platformVersion";
    String APP_PACKAGE = "appPackage";
    String APP_ACTIVITY = "appActivity";
}
