package test.authentication;

import driver.DriverFactory;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import models.pages.LoginPage;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import test.gson.LoginCredential;
import test_flows.authetication.LoginFlow;
import utils.data.DataObjectBuilder;

import java.util.Arrays;

public class LoginTest extends BaseTest {

    static {
        loginCreData = Arrays.asList(
                new LoginCredential("", ""),
                new LoginCredential("nhchung@fossil.com", "1234"));

    }

    @Test(dataProvider = "invalidLoginData")
    public void loginWithInvalidCreds(LoginCredential loginCredData) {
        DriverFactory.startAppiumServer();
        AndroidDriver<MobileElement> androidDriver = DriverFactory.getAndroidDriver();
        LoginFlow loginFlow = new LoginFlow(androidDriver);
        loginFlow.setData(loginCredData).login().verifyLogin();
//        try {
//
//            LoginPage loginPage = new LoginPage(androidDriver);
//            loginPage.bottomNavigationComp().clickOnLoginLabel();
//            //Method chaining
//            loginPage
//                    .inputUsername("nhchung@fossil.com")
//                    .inputPassword("123456")
//                    .clickOnLoginBtn();
//
//        } finally {
//            androidDriver.quit();
//        }
    }

    @Test()
    public void loginWithValidCreds() {
        String jsonLoc = "/src/main/resources/test-data/ValidLoginCred.json";
//        LoginCredential loginCredData = DataObjectBuilder.builderDataObject(jsonLoc, LoginCredential[].class)[0];
        LoginCredential loginCredData = DataObjectBuilder.builderDataObject(jsonLoc, LoginCredential.class);

        DriverFactory.startAppiumServer();
        AndroidDriver<MobileElement> androidDriver = DriverFactory.getAndroidDriver();

        LoginFlow loginFlow = new LoginFlow(androidDriver);
        loginFlow.setData(loginCredData).login().verifyLogin();

    }

    @DataProvider
    public LoginCredential[] invalidLoginData() {
        String jsonLoc = "/src/main/resources/test-data/InvalidLoginCred.json";
        return DataObjectBuilder.builderDataObject(jsonLoc, LoginCredential[].class);
    }
}