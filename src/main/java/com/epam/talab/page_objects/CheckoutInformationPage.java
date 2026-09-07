package com.epam.talab.page_objects;

import com.epam.talab.config.DriverProvider;
import com.epam.talab.utils.WaitUtils;
import org.openqa.selenium.By;

public class CheckoutInformationPage {

    private static By firstNameLocator = By.id("first-name");
    private static By lastNameLocator = By.id("last-name");
    private static By postalCodeLocator = By.id("postal-code");
    private static By continueLocator = By.id("continue");


    public void fillInformation(String firstName, String lastName, String zip) {
        WaitUtils.waitToBeDisplayed(firstNameLocator);
        DriverProvider.getDriver().findElement(firstNameLocator).sendKeys(firstName);
        DriverProvider.getDriver().findElement(lastNameLocator).sendKeys(lastName);
        DriverProvider.getDriver().findElement(postalCodeLocator).sendKeys(zip);
    }

    public void clickContinue() {
        DriverProvider.getDriver().findElement(continueLocator).click();
    }

}
