package test_flows.authetication;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import models.pages.LoginPage;
import test.gson.LoginCredential;

public class LoginFlow {

    // Class vs Object | States/ Attributes

    //IMPORTANT: Clarify Object States/Attributes

    private final AppiumDriver<MobileElement> appiumDriver;
    private LoginCredential loginCredData;

    public LoginFlow(AppiumDriver<MobileElement> appiumDriver) {
        this.appiumDriver = appiumDriver;
    }

    public LoginFlow setData(LoginCredential loginCredData)
    {
        this.loginCredData = loginCredData;
        return this;
    }

    public LoginFlow login() {
        if(loginCredData == null)
            throw new RuntimeException("Please call setData method first");

        LoginPage loginPage = new LoginPage(appiumDriver);
        loginPage.bottomNavigationComp().clickOnLoginLabel();
        //Method chaining
        loginPage
                .inputUsername(loginCredData.getUsername())
                .inputPassword(loginCredData.getPassword())
                .clickOnLoginBtn();
        return this;
    }

    public void verifyLogin() {
        boolean isEmailInvalid = isEmailInvalid(loginCredData.getUsername());
        boolean isPasswordInvalid = isPasswordInvalid(loginCredData.getPassword());

        if (isEmailInvalid)
            verifyInvalidEmailFormat();
        if (isPasswordInvalid)
            verifyInvalidPasswordlFormat();
        if (!isEmailInvalid && !isPasswordInvalid)
            verifyLoginSuccess();

    }

    private void verifyLoginSuccess() {
    }

    private void verifyInvalidPasswordlFormat() {
    }

    private boolean isPasswordInvalid(String password) {
        return true;
    }

    private void verifyInvalidEmailFormat() {
    }

    private boolean isEmailInvalid(String username) {

        // String Regex email format
        return false;
    }
}
