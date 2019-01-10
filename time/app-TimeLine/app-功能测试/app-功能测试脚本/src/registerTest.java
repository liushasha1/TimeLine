import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.URL;
import java.time.Duration;

public class registerTest {
    private AppiumDriver driver;
    private String existingPhone = "13511112222";
    private String userName = "mimi";
    private String regPassword = "12345678";
    private String regConPassword = "1234567999";
    private String regTelephone = "17717281007";

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
        //注册
        WebElement registerButton = (new WebDriverWait(driver, 2))
                .until(ExpectedConditions.presenceOfElementLocated(By.id("com.example.dingluxin.timeline:id/register")));//usrname
        registerButton.click();
        WebElement el2 = driver.findElementById("com.example.dingluxin.timeline:id/regTelephone");
        el2.sendKeys(existingPhone);
        WebElement el3 = driver.findElementById("com.example.dingluxin.timeline:id/name");
        el3.sendKeys(userName);
        WebElement el4 = driver.findElementById("com.example.dingluxin.timeline:id/regPassword");
        el4.sendKeys(regPassword);
        WebElement el5 = driver.findElementById("com.example.dingluxin.timeline:id/regConfPassword");
        el5.sendKeys(regConPassword);
        WebElement el6 = driver.findElementById("com.example.dingluxin.timeline:id/Woman");
        el6.click();
        Thread.sleep(1000);
        WebElement el7 = driver.findElementById("com.example.dingluxin.timeline:id/Man");
        el7.click();
        WebElement el8 = driver.findElementById("com.example.dingluxin.timeline:id/btn_reg");
        el8.click();
        System.out.println("填写完毕");
        //密码不一致+修改
        for (int i = 1; i < 3; i++) {
            Thread.sleep(1000);
        }
        WebElement el10 = driver.findElementById("com.example.dingluxin.timeline:id/regConfPassword");
        el10.sendKeys(regPassword);
        WebElement el11 = driver.findElementById("com.example.dingluxin.timeline:id/btn_reg");
        el11.click();
        //用户名以及存在+修改
        WebElement el12 = driver.findElementById("android:id/message");
        el12.click();
        WebElement el13 = driver.findElementById("android:id/button1");
        el13.click();
        WebElement el14 = driver.findElementById("com.example.dingluxin.timeline:id/regTelephone");
        el14.sendKeys(regTelephone);
        Thread.sleep(1000);
        WebElement el15 = driver.findElementById("com.example.dingluxin.timeline:id/btn_reg");
        el15.click();


        //注册成功
        WebElement el16 = driver.findElementById("android:id/message");
        el16.click();
        for (int i = 1; i < 3; i++) {
            Thread.sleep(1000);
        }
        WebElement el17 = driver.findElementById("android:id/button1");
        el17.click();
        Thread.sleep(1000);

        System.out.println("ok");
    }
}