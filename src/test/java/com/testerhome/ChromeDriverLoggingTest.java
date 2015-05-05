package com.testerhome;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;

import static org.junit.Assert.assertTrue;

/**
 * Created by lihuazhang on 15/4/5.
 */
public class ChromeDriverLoggingTest {

    private RemoteWebDriver driver;


    @Before
    public void createDriver() throws MalformedURLException {
        DesiredCapabilities cap = DesiredCapabilities.chrome();
        LoggingPreferences logPrefs = new LoggingPreferences();
        logPrefs.enable(LogType.PERFORMANCE, Level.ALL);
        cap.setCapability(CapabilityType.LOGGING_PREFS, logPrefs);
        driver = new RemoteWebDriver(new URL("http://127.0.0.1:9515"), cap);
    }

    @After
    public void quitDriver() {
        driver.quit();
    }

    @Test
    public void testBaiduSearch() throws InterruptedException {
        driver.get("http://www.baidu.com");
        Thread.sleep(5000);  // Let the user actually see something!
        WebElement searchBox = driver.findElement(By.name("wd"));
        searchBox.sendKeys("ChromeDriver");
        searchBox.submit();
        Thread.sleep(5000);  // Let the user actually see something!
        assertTrue(driver.getPageSource().contains("ChromeDriver"));
        for (LogEntry entry : driver.manage().logs().get(LogType.PERFORMANCE)) {
            System.out.println(entry.toString());
        }
        driver.quit();
    }
}
