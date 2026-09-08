package com.epam.talab.utils;

import com.epam.talab.config.DriverProvider;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

public class ScrollUtils {

    public static void scrollBy(int pixels){
        WebDriver driver = DriverProvider.getDriver();
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0, arguments[0])", pixels);
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
