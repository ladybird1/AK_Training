package com.epam.talab.steps;

import com.epam.talab.config.ProjectProperties;
import com.epam.talab.page_objects.*;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class CheckoutSteps {

    CheckoutOverviewPage checkoutOverviewPage = new CheckoutOverviewPage();
    @Given("User logs in with default credentials")
    public void userLogsInWithDefaultCredentials() {
        LoginPage loginPage = new LoginPage();
        loginPage.navigate();
        loginPage.login(ProjectProperties.getValue("username"), ProjectProperties.getValue("password"));
    }

    @When("User adds products to cart")
    public void userAddsProductsToCart(DataTable table) {
        List<String> products = table.asList();
        ProductsPage productsPage = new ProductsPage();
        for(String product: products) {
            productsPage.addProductToCart(product);
        }
    }

    @When("User remembers product prices")
    public void userRemembersProductPrices(DataTable table) {
        List<String> products = table.asList();
        ProductsPage productsPage = new ProductsPage();
        for(String product: products) {
            double producePrice = productsPage.getProductPrice(product);
            SharedData.productPrices.add(producePrice);
        }
    }

    @When("User opens shopping cart")
    public void openShoppingCart() {
        ProductsPage productsPage = new ProductsPage();
        productsPage.clickOnShoppingCart();
    }

    @Then("There are items in shopping cart")
    public void thereIsItemsInShoppingCart(DataTable table) {
        Map<String, Integer> expectedProducts = table.asMap(String.class, Integer.class);
        ShoppingCartPage shoppingCartPage = new ShoppingCartPage();
        List<ShoppingCartPage.ShoppingCartItem> shoppingCartItems = shoppingCartPage.getShoppingItems();
        assertEquals(expectedProducts.size(), shoppingCartItems.size(), "Shopping card does not have size: " + expectedProducts.size());

        for (ShoppingCartPage.ShoppingCartItem itemInCart : shoppingCartItems) {
            String actualProductName = itemInCart.getProductName();
            Integer expectedProductQuantity = expectedProducts.get(actualProductName);
            assertNotNull(expectedProductQuantity);
            assertEquals(expectedProductQuantity, itemInCart.getQuantity());
        }
    }

    @When("User goes through all order steps")
    public void userGoesThroughAllOrderSteps() {
        ShoppingCartPage shoppingCartPage = new ShoppingCartPage();
        shoppingCartPage.clickProceedToCheckout();
        CheckoutInformationPage checkoutInformationPage = new CheckoutInformationPage();
        checkoutInformationPage.fillInformation("Anna", "Kurta", "79026");
        checkoutInformationPage.clickContinue();

        checkoutOverviewPage.clickFinishButton();
    }

    @When("User completes order steps until Checkout review")
    public void userCompletesOrderStepsUntilCheckoutReview() {
        ShoppingCartPage shoppingCartPage = new ShoppingCartPage();
        shoppingCartPage.clickProceedToCheckout();
        CheckoutInformationPage checkoutInformationPage = new CheckoutInformationPage();
        checkoutInformationPage.fillInformation("Anna", "Kurta", "79026");
        checkoutInformationPage.clickContinue();
    }

    @Then("Order price is equal to sum of product prices")
    public void orderPriceIsEqualToSumOfProductPrices() {
        CheckoutOverviewPage checkoutOverviewPage = new CheckoutOverviewPage();
        double actualTotalPrice = checkoutOverviewPage.getItemTotalPrice();
        List<Double> productPrices = SharedData.productPrices;
        double expectedTotalPrice = 0.0;
        for (double productPrice : productPrices) {
            expectedTotalPrice = expectedTotalPrice + productPrice;
        }
        assertEquals(expectedTotalPrice, actualTotalPrice, "Total price is not equal to sum of product prices");
    }

    @When("User clicks Finish order")
    public void userCompletesOrder() {
        CheckoutOverviewPage checkoutOverviewPage = new CheckoutOverviewPage();
        checkoutOverviewPage.clickFinishButton();
    }

    @Then("Message {string} is visible")
    public void messageIsVisible(String expectedMessage) {
        CheckoutCompletePage checkoutCompletePage =new CheckoutCompletePage();
        String message = checkoutCompletePage.getCheckoutMessage();
        assertEquals(expectedMessage, message);
    }
}
