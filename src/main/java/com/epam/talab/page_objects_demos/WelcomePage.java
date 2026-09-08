package com.epam.talab.page_objects_demos;

import com.epam.talab.config.DriverProvider;
import com.epam.talab.config.ProjectProperties;
import com.epam.talab.utils.ScrollUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WelcomePage {
    private static final Logger log = LoggerFactory.getLogger(WelcomePage.class);
    private final By loginButtonLocator = By.cssSelector("[data-testid=open-login-view] a");

    public void clickLoginButton() {
        WebDriver driver = DriverProvider.getDriver();
        ScrollUtils.scrollBy(500);
        WebElement loginButtonElement = driver.findElement(loginButtonLocator);
        loginButtonElement.click();
    }

    public void navigate() {
        DriverProvider.getDriver().navigate().to(ProjectProperties.getValue("base.url"));
    }
}
