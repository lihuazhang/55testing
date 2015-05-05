package com.testerhome.android;

import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.IOException;
import java.util.logging.Level;
//import java.util.HashMap;
//import java.util.Map;

import static org.junit.Assert.assertTrue;

/**
 * Created by lihuazhang on 15/3/29.
 */
public class ChromeAndroidTest {

    private static ChromeDriverService service;
    private WebDriver driver;

    @BeforeClass
    public static void createAndStartService() throws IOException {
        System.setProperty("webdriver.chrome.logfile", "/Users/lihuazhang/Desktop/chromedriver.log");
        // TODO: there's no way to pass --verbose
        service = new ChromeDriverService.Builder()
                //.usingDriverExecutable(new File("path/to/my/chromedriver"))
                .usingAnyFreePort()
                .build();
        service.start();
    }

    @AfterClass
    public static void createAndStopService() {
        service.stop();
    }

    @Before
    public void createDriver() {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.setExperimentalOption("androidPackage", "com.android.browser");
        chromeOptions.setExperimentalOption("androidActivity", ".BrowserActivity");
//        chromeOptions.setExperimentalOption("androidUseRunningApp", true);
//        Map<String, Object> chromeOptions = new HashMap<String, Object>();
//        chromeOptions.put("androidPackage", "com.android.browser");
//        chromeOptions.put("androidActivity", ".BrowserActivity");
        DesiredCapabilities capabilities = DesiredCapabilities.chrome();
        LoggingPreferences logPrefs = new LoggingPreferences();
        logPrefs.enable(LogType.PERFORMANCE, Level.ALL);
        capabilities.setCapability(CapabilityType.LOGGING_PREFS, logPrefs);
        capabilities.setCapability(ChromeOptions.CAPABILITY, chromeOptions);
        driver = new RemoteWebDriver(service.getUrl(), capabilities);
    }

    @After
    public void quitDriver() {
        driver.quit();
    }

    @Test
    public void testBaiduSearch() throws InterruptedException {
        driver.get("http://www.baidu.com");
        Thread.sleep(5000);  // Let the user actually see something!
        WebElement searchBox = driver.findElement(By.name("word"));
        searchBox.sendKeys("ChromeDriver");
        searchBox.submit();
        Thread.sleep(5000);  // Let the user actually see something!
        assertTrue(driver.getPageSource().contains("ChromeDriver"));
        for (LogEntry entry : driver.manage().logs().get(LogType.PERFORMANCE)) {
            System.out.println(entry.toString());
        }
    }
}