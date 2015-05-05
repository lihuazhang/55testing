package com.testerhome.listen;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.internal.Base64Encoder;
import org.openqa.selenium.remote.ScreenshotException;
import org.openqa.selenium.support.events.AbstractWebDriverEventListener;

import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by lihuazhang on 15/4/18.
 */
public class MyEventListener extends AbstractWebDriverEventListener {
    @Override
    public void onException(Throwable throwable, WebDriver driver) {
        Throwable cause = throwable.getCause();
        if (cause instanceof ScreenshotException) {
            String filePath = "/Users/lihuazhang/Desktop/screenshot";
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd-hh-mm-ss");
            String daString = format.format(new Date());
            File of = new File(filePath + daString + "-exception.png");
            FileOutputStream out = null;
            try {
                out = new FileOutputStream(of);
                out.write(new Base64Encoder().decode(((ScreenshotException) cause).getBase64EncodedScreenshot()));
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    out.close();
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
        }
    }

}
