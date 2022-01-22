package test.testng;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import org.testng.Assert;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;

import java.util.List;

public class TestNGHooks {

    //Chỉ nên dùng BeforeClass bên trong Class, k dùng BeforeTest

    @Test(priority = 1)
    public void testA(){
        System.out.println("testA");
    }

    @Test(priority = 3)
    public void testB(){
        System.out.println("testB");
        String actutalValue = "testA";
        String expectedValue = "testA ";

        //Hart Assertion, nếu line 27 bị failed thì sẽ skip và ko run dòng 28
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(actutalValue,expectedValue,"[ERR] Mess 01");
        softAssert.assertTrue(actutalValue.equals(expectedValue), "[ERR] Mess 02");

        softAssert.assertAll();


//        List<MobileElement> list = appiumDriver.findElements(MobileBy.id("..."));
//        if(list.isEmpty())
//            Assert.fail("[ERR] Login Dialog Title is incorrect");
//        for (MobileElement mobileElement : list) {
//            //Verification points
        }
    }

//    @BeforeTest
//    public void beforeTest(){
//        System.out.println("beforeTest");
//    }
//
//    @BeforeClass
//    public void beforeClass(){
//        System.out.println("beforeClass");
//    }

