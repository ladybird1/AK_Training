package com.epam.talab.utils;

import com.epam.talab.config.DriverProvider;
import com.epam.talab.config.ProjectProperties;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class WaitUtils {

    private static final Logger logger = LogManager.getLogger(WaitUtils.class);


    public static void waitToBeDisplayed(By locator) {
        logger.debug("Waiting for element to be displayed by locator %s".formatted(locator));
        new WebDriverWait(DriverProvider.getDriver(), Duration.ofSeconds(ProjectProperties.getIntValue("explict.wait.timeout")))
                .until(ExpectedConditions.visibilityOfElementLocated(locator));
    }
}
