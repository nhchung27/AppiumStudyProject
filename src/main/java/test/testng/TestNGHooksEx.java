package test.testng;

import org.testng.annotations.*;

public class TestNGHooksEx {

    @Test
    public void testA(){
        System.out.println("testA");
    }

    @Test
    public void testB(){
        System.out.println("testB");
    }

    @BeforeTest
    public void beforeTest(){
        System.out.println("beforeTest");
    }

    @BeforeClass
    public void beforeClass(){
        System.out.println("beforeClass");
    }
}
