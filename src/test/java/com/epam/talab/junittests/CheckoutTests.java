package com.epam.talab.junittests;

import com.epam.talab.page_objects.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CheckoutTests extends BaseTest {


    @BeforeEach
    public void login() {
        LoginPage loginPage = new LoginPage();
        loginPage.navigate();
        loginPage.login("standard_user", "secret_sauce");
    }


    @Test
//    @Disabled
    @DisplayName("Checkout flow (one item)")
    public void testCheckoutFlowWithOneItem() {
        String itemName = "Sauce Labs Backpack";

        ProductsPage productsPage = new ProductsPage();
        productsPage.addProductToCart(itemName);
        productsPage.clickOnShoppingCart();

        ShoppingCartPage shoppingCartPage = new ShoppingCartPage();
        List<ShoppingCartPage.ShoppingCartItem> shoppingCartItems = shoppingCartPage.getShoppingItems();
        assertEquals(1, shoppingCartItems.size(), "Shopping card does not have 1 item");

        ShoppingCartPage.ShoppingCartItem addedItem = shoppingCartItems.get(0);
        assertEquals(addedItem.getProductName(), itemName);
        assertEquals(addedItem.getQuantity(), 1);

        shoppingCartPage.clickProceedToCheckout();

        CheckoutInformationPage checkoutInformationPage = new CheckoutInformationPage();
        checkoutInformationPage.fillInformation("Anna", "Kurta", "79026");
        checkoutInformationPage.clickContinue();
        CheckoutOverviewPage checkoutOverviewPage = new CheckoutOverviewPage();
        checkoutOverviewPage.clickFinishButton();

        CheckoutCompletePage checkoutCompletePage =new CheckoutCompletePage();
        String message = checkoutCompletePage.getCheckoutMessage();
        assertEquals("Thank you for your order!", message);
    }

    @Test
//    @Disabled
    @DisplayName("Checkout flow (several items)")
    public void testCheckoutFlowWithSeveralItems() {
        String itemName = "Sauce Labs Backpack";
        String secondItemName = "Sauce Labs Bolt T-Shirt";

        ProductsPage productsPage = new ProductsPage();
        double firstItemPrice = productsPage.getProductPrice(itemName);
        double secondItemPrice = productsPage.getProductPrice(secondItemName);
        productsPage.addProductToCart(itemName);
        productsPage.addProductToCart(secondItemName);
        productsPage.clickOnShoppingCart();

        ShoppingCartPage shoppingCartPage = new ShoppingCartPage();
        List<ShoppingCartPage.ShoppingCartItem> shoppingCartItems = shoppingCartPage.getShoppingItems();
        assertEquals(2, shoppingCartItems.size(), "Shopping card does not have 1 item");

        ShoppingCartPage.ShoppingCartItem addedItem = shoppingCartItems.get(0);
        assertEquals(addedItem.getProductName(), itemName);
        assertEquals(addedItem.getQuantity(), 1);

        ShoppingCartPage.ShoppingCartItem secondAddedItem = shoppingCartItems.get(1);
        assertEquals(secondAddedItem.getProductName(), secondItemName);
        assertEquals(secondAddedItem.getQuantity(), 1);

        shoppingCartPage.clickProceedToCheckout();

        CheckoutInformationPage checkoutInformationPage = new CheckoutInformationPage();
        checkoutInformationPage.fillInformation("Anna", "Kurta", "79026");
        checkoutInformationPage.clickContinue();
        CheckoutOverviewPage checkoutOverviewPage = new CheckoutOverviewPage();
        double actualTotalPrice = checkoutOverviewPage.getItemTotalPrice();
        assertEquals(firstItemPrice + secondItemPrice, actualTotalPrice, "Total price is not equal to sum of product prices");
        checkoutOverviewPage.clickFinishButton();

        CheckoutCompletePage checkoutCompletePage =new CheckoutCompletePage();
        String message = checkoutCompletePage.getCheckoutMessage();
        assertEquals("Thank you for your order!", message);
    }
}
