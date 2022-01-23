package test.authentication;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import models.pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.Test;
import test.BaseTest;
import test.gson.LoginCredential;
import test_flows.authetication.LoginFlow;
import utils.data.DataObjectBuilder;

import java.util.Date;

public class LoginTestWithBaseTest extends BaseTest {

    @Test()
    public void loginWithValidCreds() {
        String jsonLoc = "/src/main/resources/test-data/ValidLoginCred.json";
//        LoginCredential loginCredData = DataObjectBuilder.builderDataObject(jsonLoc, LoginCredential[].class)[0];
        LoginCredential loginCredData = DataObjectBuilder.builderDataObject(jsonLoc, LoginCredential.class);
        AppiumDriver<MobileElement> androidDriver = getAndroidDriver();
        LoginFlow loginFlow = new LoginFlow(androidDriver);
        loginFlow.setData(loginCredData).login().verifyLogin();

    }

    @Test
    public void testMethod02(){
        LoginPage loginPage = new LoginPage(getAndroidDriver());
        loginPage.bottomNavigationComp().clickOnLoginLabel();
    }
}