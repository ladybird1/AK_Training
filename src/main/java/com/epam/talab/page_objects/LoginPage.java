package com.epam.talab.page_objects;

import com.epam.talab.config.DriverProvider;
import org.openqa.selenium.By;

public class LoginPage {

    private final By userNameInput = By.id("user-name");

    private final By passwordInput = By.id("password");

    private final By submitButton = By.id("login-button");


    public void fillUsername(String username) {
        DriverProvider.getDriver().findElement(userNameInput).sendKeys(username);
    }

    public void fillPassword(String password) {
        DriverProvider.getDriver().findElement(passwordInput).sendKeys(password);
    }

    public void submit() {
        DriverProvider.getDriver().findElement(submitButton).click();
    }

    public void login(String username, String password) {
        fillUsername(username);
        fillPassword(password);
        submit();
    }

    public void navigate() {
        DriverProvider.getDriver().navigate().to("https://www.saucedemo.com/");
    }

}
