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

public class loginTest {
    private AppiumDriver driver;
    private String errorPhone = "10011110022";
    private String errorPassword = "123";
    private String truePhone= "13511112222";
    private String truePassword = "1";
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
        WebElement phoneTextFeild = (new WebDriverWait(driver, 1))
                .until(ExpectedConditions.presenceOfElementLocated(By.id("com.example.dingluxin.timeline:id/telephone")));//usrname
        phoneTextFeild.sendKeys(errorPhone);

        WebElement passwordTextField = driver.findElement(By.id("com.example.dingluxin.timeline:id/password"));//password
        passwordTextField.sendKeys(truePassword);

        WebElement loginButton = driver.findElement(By.id("com.example.dingluxin.timeline:id/btn_login"));//login
        loginButton.click();
        WebElement el4 = (WebElement) driver.findElementById("android:id/button1");
        el4.click();
        //用户明不存在+修改
        WebElement el5 = ( WebElement) driver.findElementById("com.example.dingluxin.timeline:id/telephone");
        el5.sendKeys(truePhone);
        WebElement el6 = ( WebElement) driver.findElementById("com.example.dingluxin.timeline:id/password");
        el6.sendKeys(errorPassword);
        WebElement el7 = ( WebElement) driver.findElementById("com.example.dingluxin.timeline:id/btn_login");
        el7.click();
        WebElement el8 = ( WebElement) driver.findElementById("android:id/button1");
        el8.click();
        //密码错误+修改
        WebElement el9 = ( WebElement) driver.findElementById("com.example.dingluxin.timeline:id/password");
        el9.sendKeys(truePassword);
        WebElement el10 = ( WebElement) driver.findElementById("com.example.dingluxin.timeline:id/btn_login");
        el10.click();
        //登陆结束进入新闻页面
        System.out.println("sleep1");
        for (int i = 1; i < 3; i++) {
            Thread.sleep(1000);
        }

    }

}
