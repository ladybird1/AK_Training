package com.epam.talab.page_objects_demos;

import com.epam.talab.config.DriverProvider;
import com.epam.talab.utils.WaitUtils;
import org.openqa.selenium.By;

public class MyNotesPage {
    private final By logoutButton = By.cssSelector("[data-testid=logout]");
    public boolean isLogoutButtonVisible(){
        WaitUtils.waitToBeDisplayed(logoutButton);
        return DriverProvider.getDriver().findElement(logoutButton).isDisplayed();
    }
}
