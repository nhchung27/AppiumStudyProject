package test.testng;

import driver.DriverFactory;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import models.pages.LoginPage;
import org.testng.annotations.Test;

public class LoginFormTest {

    @Test
    public void loginWithValidCredentials(){

        DriverFactory.startAppiumServer();
        AndroidDriver<MobileElement> androidDriver = DriverFactory.getAndroidDriver();

        try {

            LoginPage loginPage = new LoginPage(androidDriver);
            loginPage.bottomNavigationComp().clickOnLoginLabel();
            //Method chaining
            loginPage
                    .inputUsername("nhchung@fossil.com")
                    .inputPassword("123456")
                    .clickOnLoginBtn();

        } finally {
            androidDriver.quit();
        }
    }
    @Test
    public  void testSth(){
        System.out.println("Test Something");
    }
}
