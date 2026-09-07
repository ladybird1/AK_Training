package com.epam.talab.steps;

import com.epam.talab.config.DriverProvider;
import io.cucumber.java.After;
import io.cucumber.java.Scenario;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;


public class Hooks {

    private static final Logger logger = LogManager.getLogger(Hooks.class);

    @After
    public void after(Scenario scenario) {
        logger.debug("Taking screenshot and releasing driver");
        WebDriver driver = DriverProvider.getDriver();
        try {
            if (driver != null) {
                byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
                scenario.attach(screenshot, "image/png", "screenshot");
            }
        } finally {
            DriverProvider.releaseDriver();
        }
    }
}
