package api_learning;

import driver.DriverFactory;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class SwipeVertically {
    public static void main(String[] args) throws InterruptedException {
        DriverFactory.startAppiumServer();
        AndroidDriver<MobileElement> androidDriver = DriverFactory.getAndroidDriver();
        MobileElement formsLabel = androidDriver.findElementByAccessibilityId("Forms");
        formsLabel.click();

        // Get Mobile size
        Dimension windowSize = androidDriver.manage().window().getSize();
        int screenHeight = windowSize.getHeight();
        int screenWidth = windowSize.getWidth();

        //Calculate touch points
        int xStartPoint = (50 * screenWidth) / 100;
        int xEndPoint = xStartPoint;
        int yStartPoint = (90 * screenHeight) / 100;
        int yEndPoint = (10 * screenHeight) / 100;

        // Convert to PointOptions - Coordinates
        PointOption startPoint = new PointOption().withCoordinates(xStartPoint,yStartPoint);
        PointOption endPoint = new PointOption().withCoordinates(xEndPoint,yEndPoint);

        // Avoid screen trasition
        // Check to see whether we are on Forms Screen
        WebDriverWait wait = new WebDriverWait(androidDriver, 5L);
        wait.until(ExpectedConditions.visibilityOf(androidDriver.findElementByAccessibilityId("switch")));

        // Perform Actions
        // press -> move up -> release
        TouchAction touchAction = new TouchAction(androidDriver);
        TouchAction perform = touchAction
                .press(startPoint)
                .waitAction(new WaitOptions().withDuration(Duration.ofSeconds(2)))
                .moveTo(endPoint)
                .release()
                .perform();

        Thread.sleep(3000);

        // Find Active Button
        MobileElement activeBtnElem = androidDriver.findElementByAccessibilityId("button-Active");

        // Click on Active button
        activeBtnElem.click();
    }
}
