package com.epam.talab.page_objects_demos;

import com.epam.talab.config.DriverProvider;
import com.epam.talab.utils.ScrollUtils;
import com.epam.talab.utils.WaitUtils;
import org.openqa.selenium.By;

public class NotesLoginPage {

    private final By emailAddressInput = By.id("email");
    private final By passwordInput = By.id("password");
    private final By loginButton = By.cssSelector("[data-testid=login-submit]");
    private final By errorMessage = By.cssSelector("[data-testid=alert-message]");
    private final By hintEmailErrorMessage = By.cssSelector("#email + .invalid-feedback");
    private final By hintPasswordErrorMessage = By.cssSelector("#password + .invalid-feedback");


    public void fillEmailAddress(String emailAddress) {
        ScrollUtils.scrollBy(500);
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

    public boolean isErrorMessageVisible(){
        WaitUtils.waitToBeDisplayed(errorMessage);
        return DriverProvider.getDriver().findElement(errorMessage).isDisplayed();
    }

    public boolean isEmailErrorMessageVisible() {
        WaitUtils.waitToBeDisplayed(hintEmailErrorMessage);
        return DriverProvider.getDriver().findElement(hintEmailErrorMessage).isDisplayed();
    }

    public boolean isPasswordErrorMessageVisible() {
        WaitUtils.waitToBeDisplayed(hintPasswordErrorMessage);
        return DriverProvider.getDriver().findElement(hintPasswordErrorMessage).isDisplayed();
    }

    public void clickLogin() {
        DriverProvider.getDriver().findElement(loginButton).click();
        WaitUtils.waitToBeDisplayed(loginButton);
    }

}


