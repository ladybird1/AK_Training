package com.epam.talab.page_objects;

import com.epam.talab.config.DriverProvider;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.NoSuchElementException;

public class ProductsPage {

    private final By productItem = By.cssSelector("[data-test='inventory-item']");

    private final By addToCartButton = By.xpath(".//button[contains(@id, 'add-to-cart')]");
    private final By itemPriceLocator = By.cssSelector(".inventory_item_price");

    private final By shoppingCartButton = By.id("shopping_cart_container");


    public List<WebElement> getProducts() {
        return DriverProvider.getDriver().findElements(productItem);
    }

    public WebElement getProduct(String productName) {
        return getProducts().stream().filter(product -> product.getText().contains(productName))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("No product with name %s".formatted(productName)));
    }

    public void addProductToCart(String productName) {
        getProduct(productName).findElement(addToCartButton).click();
    }

    public Double getProductPrice(String productName) {
        String priceStr = getProduct(productName).findElement(itemPriceLocator).getText();
        priceStr = priceStr.replaceAll("\\$", "");
        return Double.parseDouble(priceStr);
    }

    public void clickOnShoppingCart() {
        DriverProvider.getDriver().findElement(shoppingCartButton).click();
    }


}
