package com.epam.talab.page_objects;

import com.epam.talab.config.DriverProvider;
import org.openqa.selenium.By;

public class CheckoutCompletePage {

    private static By checkoutMessage = By.className("complete-header");

    public String getCheckoutMessage() {
        return DriverProvider.getDriver().findElement(checkoutMessage).getText();
    }
}
