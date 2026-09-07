package com.epam.talab.page_objects_demos;

import com.epam.talab.config.DriverProvider;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WelcomePage {
    private static final Logger log = LoggerFactory.getLogger(WelcomePage.class);
    private final By loginButtonLocator = By.cssSelector("[data-testid=open-login-view] a");

    public void clickLoginButton() {
        WebDriver driver = DriverProvider.getDriver();
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,500)");
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        WebElement loginButtonElement = driver.findElement(loginButtonLocator);
        loginButtonElement.click();
    }

    public void navigate() {
        DriverProvider.getDriver().navigate().to("https://practice.expandtesting.com/notes/app/");
    }
}
