package api_learning;

import driver.DriverFactory;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class Lession13 {
    public static void main(String[] args) {
        DriverFactory.startAppiumServer();
        AndroidDriver<MobileElement> androidDriver = DriverFactory.getAndroidDriver();

        try {
            //Click on Login lable
            MobileElement loginLableElem = androidDriver.findElementByAccessibilityId("Login");
            loginLableElem.click();

            final int EMAIL_FIELD_INDEX = 0;
            final int PASSWORD_FIELD_INDEX = 1;
            List<MobileElement> loginFormInputElems = androidDriver.findElements(By.xpath("//android.widget.EditText"));
            if(loginFormInputElems.isEmpty()){
                throw new RuntimeException("Login form elements can't be found !");
            } else {
                loginFormInputElems.get(EMAIL_FIELD_INDEX).sendKeys("nhchung@fossil.com"); //Magic numbers
                loginFormInputElems.get(PASSWORD_FIELD_INDEX).sendKeys("12345678");
            }

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

            WebDriverWait wait = new WebDriverWait(androidDriver, 10L);
            wait.until(ExpectedConditions.visibilityOf(androidDriver.findElement(By.id("android:id/alertTitle"))));
            MobileElement loginDialogTitleElem = androidDriver.findElement(By.id("android:id/alertTitle"));
            System.out.println("Login Title:" + loginDialogTitleElem.getText());

            MobileElement loginDialogXpathElem = androidDriver.findElementByXPath(
                    "//*[@resource-id='android:id/topPanel']//*[@resource-id='android:id/alertTitle']");
            System.out.println(loginDialogXpathElem.getText());

            List<MobileElement> badSelectorElems = androidDriver.findElementsByXPath("wrong"); //không xảy ra lỗi nếu k tìm thấy element
            if (!badSelectorElems.isEmpty()){ //nếu list rỗng thì mới thoả điều kiện
                throw new RuntimeException("Wrong Element still displayed !!");
            }

            System.out.println(badSelectorElems.get(0).getText());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            androidDriver.quit();
        }

    }
}
