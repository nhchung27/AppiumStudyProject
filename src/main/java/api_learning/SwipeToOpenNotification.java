package api_learning;

import driver.DriverFactory;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class SwipeToOpenNotification {
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
        int yStartPoint = 0;
        int yEndPoint = (50 * screenHeight) / 100;

        // Convert to PointOptions - Coordinates
        PointOption startPoint = new PointOption().withCoordinates(xStartPoint,yStartPoint);
        PointOption endPoint = new PointOption().withCoordinates(xEndPoint,yEndPoint);


        // Perform Actions
        // press -> move up -> release
        TouchAction touchAction = new TouchAction(androidDriver);
        touchAction
                .press(startPoint)
                .waitAction(new WaitOptions().withDuration(Duration.ofSeconds(1)))
                .moveTo(endPoint)
                .release()
                .perform();

        //Tap Expand button on List notification to show the body
        By expandButtons = MobileBy.id("android:id/expand_button");
        List<MobileElement> expandElems = androidDriver.findElements(expandButtons);
        int n = expandElems.size();
        for (int i = 0; i < n; i++) {
            expandElems.get(i).click();
            Thread.sleep(2);
        }

        // Tìm 1 element từ 1 element khác, narrow down cái scope tìm kiếm
        List<MobileElement> notificationElems = androidDriver.findElements(By.id("android:id/notification_main_column")); //android:id/status_bar_latest_event_content
        List<Notification> notifications = new ArrayList<>();

        // Functional Interface + Lambda expression
        //Anonymous function | returnType methodName() {} | () - > {}
        notificationElems.forEach(notificationElem -> {
            String notificationTitle = notificationElem.findElement(By.id("android:id/title")).getText(); // android:id/notification_main_column //android:id/app_name_text
            By bigTextById = MobileBy.id("android:id/big_text"); //android:id/big_text
            By textById = MobileBy.id("android:id/text"); //android:id/notification_main_column  android:id/text android:id/title

            List<MobileElement> bigTextElems = androidDriver.findElements(bigTextById);
            List<MobileElement> textElems = androidDriver.findElements(textById);
            List<MobileElement> notificationBodyElems = !bigTextElems.isEmpty() ? bigTextElems : textElems;
            String notificationBody = notificationBodyElems.isEmpty() ? null : notificationBodyElems.get(0).getText();

            notifications.add(new Notification(notificationTitle,notificationBody));

        });
        // Verification
        notifications.forEach(notification -> {
            System.out.println(notification);
        });


//        Thread.sleep(2000);
//         Swipe up to close notification bar
        touchAction
                .press(endPoint)
                .waitAction(new WaitOptions().withDuration(Duration.ofSeconds(2)))
                .moveTo(startPoint)
                .release()
                .perform();

        Thread.sleep(2000);
    }
    public static class Notification {
        private final String title;
        private final String content;

        public Notification(String title, String content) {
            this.title = title;
            this.content = content;
        }

        public String getTitle() {
            return title;
        }

        public String getContent() {
            return content;
        }

        @Override
        public String toString() {
            return "Notification{" +
                    "title='" + title + '\'' +
                    ", content='" + content + '\'' +
                    '}';
        }
    }
}
