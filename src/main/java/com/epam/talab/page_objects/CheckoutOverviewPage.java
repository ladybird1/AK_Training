package com.epam.talab.page_objects;

import com.epam.talab.config.DriverProvider;
import org.openqa.selenium.By;

public class CheckoutOverviewPage {

    private static By finishButtonLocator = By.id("finish");

    private static By itemTotalPriceLocator = By.cssSelector(".summary_subtotal_label");

    public void clickFinishButton() {
        DriverProvider.getDriver().findElement(finishButtonLocator).click();
    }

    public double getItemTotalPrice() {
        String totalLabelText = DriverProvider.getDriver().findElement(itemTotalPriceLocator).getText();
        totalLabelText = totalLabelText.replaceAll(".*\\$", "");
        return Double.parseDouble(totalLabelText);
    }
}
