package com.epam.talab.page_objects_demos;

import com.epam.talab.config.DriverProvider;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

public class LoginPageLoginAsUser {

    private final By emailAddressInput = By.id("email");
    private final By passwordInput = By.id("password");
    private final By loginButton = By.cssSelector("[data-testid=login-submit]");


    public void fillEmailAddress(String emailAddress) {
        WebDriver driver = DriverProvider.getDriver();
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,500)");
        DriverProvider.getDriver().findElement(emailAddressInput).sendKeys(emailAddress);
    }

    public void fillPassword(String password) {
        DriverProvider.getDriver().findElement(passwordInput).sendKeys(password);
    }

    public void login(String emailAddress, String password) {
        fillEmailAddress(emailAddress);
        fillPassword(password);
        clickLogin();
    }

    public void clickLogin() {
        DriverProvider.getDriver().findElement(loginButton).click();
    }

}


