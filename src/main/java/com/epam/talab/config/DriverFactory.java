package com.epam.talab.config;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

public class DriverFactory {

    private static final Logger logger = LogManager.getLogger(DriverFactory.class);
    public static WebDriver getDriver(String driverType) {
        logger.debug("Providing driver for type: %s".formatted(driverType));

        switch (driverType) {
            case "chrome":
                return configureChromeDriver();
            case "firefox":
                return configureFirefoxDriver();
            default:
                throw new RuntimeException("No driver for :" + driverType);
        }
    }

    public static WebDriver configureChromeDriver() {
        ChromeOptions options = new ChromeOptions();

        Map<String, Object> prefs = new HashMap<>();
        prefs.put("credentials_enable_service", false);
        prefs.put("profile.password_manager_enabled", false);
        prefs.put("profile.password_manager_leak_detection", false);
        options.setExperimentalOption("prefs", prefs);
        options.addArguments("--disable-features=PasswordLeakDetection");
        WebDriver driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(ProjectProperties.getIntValue("implicit.wait.timeout")));
        driver.manage().window().maximize();
        return driver;
    }

    public static WebDriver configureFirefoxDriver() {
        WebDriver driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(ProjectProperties.getIntValue("implicit.wait.timeout")));
        driver.manage().window().maximize();
        return driver;
    }
}
