import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.PerformsTouchActions;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.android.Connection;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import javafx.scene.web.WebEngine;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import sun.security.krb5.internal.crypto.Des;

import java.io.File;
import java.net.URL;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static junit.framework.Assert.assertEquals;


public class MyKomootTest {
    private AppiumDriver driver;
    private String invalidPhone = "13511112222";
    private String randomPassword = "1";
    private String expectedErrorMessage = "error";

    @Before
    public void setUp() throws Exception {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("app", "/Users/greenxiaye2/Desktop/Timeline/app/build/outputs/apk/debug/debug.apk");
        capabilities.setCapability("deviceName", "Nexus");
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("platformVersion", "7.0");
        capabilities.setCapability("appPackage", "com.example.dingluxin.timeline");
        capabilities.setCapability("appActivity", "com.example.dingluxin.timeline.LoginActivity");

        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
    }

    @After
    public void tearDown() throws Exception {
        driver.quit();
    }


    @Test
    public void logInWithInvalidTelephone() throws InterruptedException {
        //登陆
        WebElement phoneTextFeild = (new WebDriverWait(driver, 60))
                .until(ExpectedConditions.presenceOfElementLocated(By.id("com.example.dingluxin.timeline:id/telephone")));//usrname
        phoneTextFeild.sendKeys(invalidPhone);
        for (int i = 1; i < 5; i++) {
            Thread.sleep(1000);
        }
        WebElement passwordTextField = driver.findElement(By.id("com.example.dingluxin.timeline:id/password"));//password
        passwordTextField.sendKeys(randomPassword);

        WebElement loginButton = driver.findElement(By.id("com.example.dingluxin.timeline:id/btn_login"));//login
        loginButton.click();
        //登陆结束进入新闻页面
        System.out.println("sleep1");
/*
        //插入数据
        WebElement refreshButton = driver.findElement(By.id("com.example.dingluxin.timeline:id/btn_refresh"));//login
        refreshButton.click();
        System.out.println("点击按钮了");
        for (int i = 1; i < 10; i++) {
            Thread.sleep(1000);
        }*/
        int width = driver.manage().window().getSize().width;
        int height = driver.manage().window().getSize().height;
        Duration duration=Duration.ofSeconds(1);
        TouchAction action1=new TouchAction(driver)
                .press(PointOption.point(width/2, height*3/4)).waitAction(WaitOptions.waitOptions(duration))
                .moveTo(PointOption.point(width/2,height/4)).release();
        action1.perform();
        System.out.println("下滑好了");


        /*
        System.out.println("英国更新出来了啊");
        List<WebElement>noteList=driver.findElementsByClassName("android.widget.TextView");
        WebElement latestNote = noteList.get(0);
        for (int i = 1; i <5; i++) {
            Thread.sleep(1000);
        }
        System.out.println("sleep1");
        //List<WebElement>noteList=driver.findElementsByClassName("android.widget.TextView");
        WebElement latestNote1 = noteList.get(10);
        for (int i = 1; i < 10; i++) {
            Thread.sleep(900000000);
        }
        System.out.println("sleep2");
        // driver.execute_script("location.reload()");
/*
        WebElement errorMessage=(new WebDriverWait(driver,60))
                .until(ExpectedConditions.presenceOfElementLocated(By.id("")));//message
        assertEquals(errorMessage.getText(),expectedErrorMessage);*/

        Thread.sleep(1000);

        WebElement moreButton = driver.findElement(By.id("com.example.dingluxin.timeline:id/btn_more"));
        moreButton.click();
        System.out.println("点击更多了");
        for (int i = 1; i < 10; i++) {
            Thread.sleep(1000);
        }

        WebElement comm = (new WebDriverWait(driver, 60))
                .until(new ExpectedCondition<WebElement>() {
                    @Override
                    public WebElement apply(WebDriver d) {
                        TouchAction action1=new TouchAction(driver)
                                .press(PointOption.point(width/2, height*9/10)).waitAction(WaitOptions.waitOptions(duration))
                                .moveTo(PointOption.point(width/2,height/10)).release();
                        action1.perform();
                        WebElement moreButton1 = driver.findElement(By.id("com.example.dingluxin.timeline:id/btn_more"));
                        return moreButton1;
                    }
                });




        Duration duration2=Duration.ofSeconds(500);
        TouchAction action2=new TouchAction(driver)
                .press(PointOption.point(width/2, height*9/10)).waitAction(WaitOptions.waitOptions(duration))
                .moveTo(PointOption.point(width/2,height/10)).release();
        action2.perform();
        System.out.println("下滑好了");

        Thread.sleep(1000);



        WebElement moreButton1 = driver.findElement(By.id("com.example.dingluxin.timeline:id/btn_more"));
        moreButton1.click();
        System.out.println("点击更多了");
        for (int i = 1; i < 10; i++) {
            Thread.sleep(1000);
        }

    }


}