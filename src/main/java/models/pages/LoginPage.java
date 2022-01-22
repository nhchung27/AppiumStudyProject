package models.pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import models.components.global.BottomNavigationComponent;
import org.openqa.selenium.By;

public class LoginPage {

    private final AppiumDriver<MobileElement> appiumDriver;
    private static final By usernameSelector = MobileBy.AccessibilityId("input-email");
    private static final By passwordSelector = MobileBy.AccessibilityId("input-password");
    private static final By loginBtnSelector = MobileBy.AccessibilityId("button-LOGIN");


    public LoginPage(AppiumDriver<MobileElement> appiumDriver) {

        this.appiumDriver = appiumDriver;
    }

    // Return found element(s)
    // Cách 1
    private MobileElement usernameElem(){

        return appiumDriver.findElement(usernameSelector);
    }
    // Cách 2
    public LoginPage inputUsername(String username){

        usernameElem().sendKeys(username);
        return this;
    }

    public LoginPage inputPassword(String password){

        appiumDriver.findElement(passwordSelector).sendKeys(password);
        return this;
    }

    public void clickOnLoginBtn(){

        appiumDriver.findElement(loginBtnSelector).click();
    }

    public BottomNavigationComponent bottomNavigationComp(){
        return new BottomNavigationComponent(appiumDriver);
    }
}
