package com.epam.talab.page_objects;

import com.epam.talab.config.DriverProvider;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.stream.Collectors;

public class ShoppingCartPage {


    private By shoppingItemLocator = By.className("cart_item");
    private By checkoutButtonLocator = By.id("checkout");

    public List<ShoppingCartItem> getShoppingItems() {
        return DriverProvider.getDriver().findElements(shoppingItemLocator).stream()
                .map(ShoppingCartItem::new)
                .collect(Collectors.toList());
    }

    public void clickProceedToCheckout() {
        DriverProvider.getDriver().findElement(checkoutButtonLocator).click();
    }


    public class ShoppingCartItem {

        private static By quantityLocator = By.cssSelector("[data-test='item-quantity']");
        private static By itemNameLocator = By.cssSelector("[data-test='inventory-item-name']");
        private final WebElement ROOT;
        public ShoppingCartItem(WebElement root) {
            this.ROOT = root;
        }

        public int getQuantity() {
            return Integer.parseInt(ROOT.findElement(quantityLocator).getText());
        }

        public String getProductName() {
            return ROOT.findElement(itemNameLocator).getText();
        }

    }
}
