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

public class allTest {

    private AppiumDriver driver;
    private String existingPhone = "10011112222";
    private String userName = "mimi";
    private String regPassword = "12345678";
    private String regConPassword = "1234567999";
    private String regTelephone = "17717281005";

    private String errorPhone = "10011110022";
    private String errorPassword = "123";
    private String truePhone= "10011112222";
    private String truePassword = "60704226";

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
        /********************************注册**************************/
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
        /********************************登陆**************************/
        //登陆
        WebElement phoneTextFeild = (new WebDriverWait(driver, 1))
                .until(ExpectedConditions.presenceOfElementLocated(By.id("com.example.dingluxin.timeline:id/telephone")));//usrname
        phoneTextFeild.sendKeys(errorPhone);

        WebElement passwordTextField = driver.findElement(By.id("com.example.dingluxin.timeline:id/password"));//password
        passwordTextField.sendKeys(truePassword);

        WebElement loginButton = driver.findElement(By.id("com.example.dingluxin.timeline:id/btn_login"));//login
        loginButton.click();
        WebElement el24 = (WebElement) driver.findElementById("android:id/button1");
        el24.click();
        //用户明不存在+修改
        WebElement el25 = ( WebElement) driver.findElementById("com.example.dingluxin.timeline:id/telephone");
        el25.sendKeys(truePhone);
        WebElement el26 = ( WebElement) driver.findElementById("com.example.dingluxin.timeline:id/password");
        el26.sendKeys(errorPassword);
        WebElement el27 = ( WebElement) driver.findElementById("com.example.dingluxin.timeline:id/btn_login");
        el27.click();
        WebElement el28 = ( WebElement) driver.findElementById("android:id/button1");
        el28.click();
        //密码错误+修改
        WebElement el29 = ( WebElement) driver.findElementById("com.example.dingluxin.timeline:id/password");
        el29.sendKeys(truePassword);
        WebElement el30 = ( WebElement) driver.findElementById("com.example.dingluxin.timeline:id/btn_login");
        el30.click();
        //登陆结束进入新闻页面
        System.out.println("sleep1");
        for (int i = 1; i < 3; i++) {
            Thread.sleep(1000);
        }
        /********************************更新**************************/
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
        Duration duration=Duration.ofSeconds(5);
        TouchAction action1=new TouchAction(driver)
                .press(PointOption.point(width/2, height*3/4)).waitAction(WaitOptions.waitOptions(duration))
                .moveTo(PointOption.point(width/2,height/4)).release();
        action1.perform();
        System.out.println("下滑好了");

        for (int i = 1; i < 10; i++) {
            Thread.sleep(1000);
        }
        /********************************更多**************************/
        //下滑

        TouchAction action41 = new TouchAction(driver)
                .press(PointOption.point(width / 2, height * 3 / 4)).waitAction(WaitOptions.waitOptions(duration))
                .moveTo(PointOption.point(width / 2, height / 4)).release();
        action41.perform();
        System.out.println("下滑好了");
        Thread.sleep(1000);
        //上滑
        TouchAction action2 = new TouchAction(driver)
                .press(PointOption.point(width / 2, height / 4)).waitAction(WaitOptions.waitOptions(duration))
                .moveTo(PointOption.point(width / 2, height * 3 / 4)).release();
        action2.perform();
        System.out.println("上滑好了");

        for (int i = 1; i < 2; i++) {
            Thread.sleep(1000);
        }
        //下滑到更多 两次
        for(int i=0;i<2;i++){
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

            Thread.sleep(1000);
            //点击更多
            WebElement moreButton1 = driver.findElement(By.id("com.example.dingluxin.timeline:id/btn_more"));
            moreButton1.click();
            System.out.println("点击更多了");
            for (int  j= 1; j < 5; j++) {
                Thread.sleep(1000);
            }
        }
        //再下滑一下
        TouchAction action3 = new TouchAction(driver)
                .press(PointOption.point(width / 2, height * 3 / 4)).waitAction(WaitOptions.waitOptions(duration))
                .moveTo(PointOption.point(width / 2, height / 4)).release();
        action3.perform();
        System.out.println("下滑好了");
    }
}
