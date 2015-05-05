package com.testerhome;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.Assert.assertTrue;

/**
 * Created by lihuazhang on 15/3/29.
 */
public class ChromeDriverTestWebPage {

    @Test
    public void testBaiduSearch() throws InterruptedException {
        // Optional, if not specified, WebDriver will search your path for chromedriver.
        // System.setProperty("webdriver.chrome.driver", "/path/to/chromedriver");
        WebDriver driver = new ChromeDriver();
        driver.get("http://www.baidu.com");
        Thread.sleep(5000);  // Let the user actually see something!
        WebElement searchBox = driver.findElement(By.name("wd"));
        searchBox.sendKeys("ChromeDriver");
        searchBox.submit();
        Thread.sleep(5000);  // Let the user actually see something!
        assertTrue(driver.getPageSource().contains("ChromeDriver"));
        driver.quit();
    }
}
