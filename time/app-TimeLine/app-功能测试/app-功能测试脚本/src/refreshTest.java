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

public class refreshTest {

    private AppiumDriver driver;
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
        phoneTextFeild.sendKeys(truePhone);

        WebElement passwordTextField = driver.findElement(By.id("com.example.dingluxin.timeline:id/password"));//password
        passwordTextField.sendKeys(truePassword);

        WebElement loginButton = driver.findElement(By.id("com.example.dingluxin.timeline:id/btn_login"));//login
        loginButton.click();
        //登陆结束进入新闻页面
        System.out.println("sleep1");
        for (int i = 1; i < 3; i++) {
            Thread.sleep(1000);
        }

        //插入数据
        for (int i = 1; i < 25; i++) {
            Thread.sleep(1000);
        }
        //点击更新
        WebElement refreshButton = driver.findElement(By.id("com.example.dingluxin.timeline:id/btn_refresh"));//login
        refreshButton.click();
        System.out.println("点击按钮了");
        for (int i = 1; i < 10; i++) {
            Thread.sleep(1000);
        }
        //下滑
        int width = driver.manage().window().getSize().width;
        int height = driver.manage().window().getSize().height;
        Duration duration=Duration.ofSeconds(10);
        TouchAction action1=new TouchAction(driver)
                .press(PointOption.point(width/2, height*3/4)).waitAction(WaitOptions.waitOptions(duration))
                .moveTo(PointOption.point(width/2,height/4)).release();
        action1.perform();
        System.out.println("下滑好了");

        for (int i = 1; i < 10; i++) {
            Thread.sleep(1000);
        }

    }
}