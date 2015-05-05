package com.testerhome;

import com.testerhome.listen.MyEventListener;
import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.events.WebDriverEventListener;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import static org.junit.Assert.assertTrue;

/**
 * Created by lihuazhang on 15/3/29.
 */
public class ChromeTest {

    private static ChromeDriverService service;
    private WebDriver driver;

    @BeforeClass
    public static void createAndStartService() throws IOException {
//        service = new ChromeDriverService.Builder()
//                //.usingDriverExecutable(new File("path/to/my/chromedriver"))
//                .usingAnyFreePort()
//                .build();
//        service.start();
    }

    @AfterClass
    public static void createAndStopService() {
        service.stop();
    }

    @Before
    public void createDriver() throws MalformedURLException {
        driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"),
                DesiredCapabilities.firefox());
        WebDriverEventListener eventListener = new MyEventListener();
        driver = new EventFiringWebDriver(driver).register(eventListener);
    }

    @After
    public void quitDriver() {
        driver.quit();
    }

    @Test
    public void testBaiduSearch() throws InterruptedException {
        driver.get("http://www.baidu.com");
        Thread.sleep(5000);  // Let the user actually see something!
        WebElement searchBox = driver.findElement(By.name("wd1"));
        searchBox.sendKeys("ChromeDriver");
        searchBox.submit();
        Thread.sleep(5000);  // Let the user actually see something!
        assertTrue(driver.getPageSource().contains("ChromeDriver"));
    }
}