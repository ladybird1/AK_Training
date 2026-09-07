package com.epam.talab.config;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;

public class DriverProvider {

    private static final Logger logger = LogManager.getLogger(DriverProvider.class);
    private final static ThreadLocal<WebDriver> DRIVER_POOL = new ThreadLocal<>();


    public static WebDriver getDriver() {
        if (DRIVER_POOL.get() == null) {
            logger.debug("Initializing new driver");
            DRIVER_POOL.set(DriverFactory.getDriver(ProjectProperties.getValue("driver")));
        }
        return DRIVER_POOL.get();
    }

    public static void releaseDriver() {
        logger.debug("Releasing driver");
        WebDriver driver = DRIVER_POOL.get();

        if (driver != null) {
            driver.quit();
            DRIVER_POOL.set(null);
        }
    }
}
