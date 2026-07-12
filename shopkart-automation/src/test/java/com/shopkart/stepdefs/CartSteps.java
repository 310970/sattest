package com.shopkart.stepdefs;

import com.shopkart.api.CartClient;
import com.shopkart.api.ProductClient;
import com.shopkart.data.builders.CartBuilder;
import com.shopkart.support.WorldContext;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public final class CartSteps {
    private final CartClient cartClient = new CartClient();
    private final ProductClient productClient = new ProductClient();
    private final WorldContext world;

    public CartSteps(WorldContext world) {
        this.world = world;
    }

    public CartBuilder.BuiltCart cartFor(String persona, String sku, int quantity) {
        return CartBuilder.aCart().forCustomer(persona).withItem(sku, quantity).build();
    }

    @When("she adds {int} x {string} to a new cart")
    public void sheAddsToNewCart(int quantity, String sku) {
        world.cartId = cartClient.create(world.token).then().statusCode(201).extract().path("cartId");
        world.response = cartClient.addItem(world.token, world.cartId, sku, quantity);
    }

    @When("she adds {int} x {string} ({int} paise each) to her cart")
    public void sheAddsToHerCart(int quantity, String sku, int pricePaise) {
        sheAddsToNewCart(quantity, sku);
    }

    @Then("the cart total is {int} paise")
    public void cartTotalIs(int expectedTotal) {
        world.response.then().statusCode(200);
        int actualTotal = world.response.path("totalPaise");
        assertEquals(expectedTotal, actualTotal);
    }

    @When("a shopper searches products for {string}")
    public void shopperSearchesProducts(String query) {
        world.response = productClient.search(query);
    }

    @Then("the product results include {string}")
    public void productResultsInclude(String sku) {
        world.response.then().statusCode(200);
        assertTrue(world.response.jsonPath().getList("sku").contains(sku), "Expected product result: " + sku);
    }

    @Then("the response status is {int}")
    public void responseStatusIs(int expectedStatus) {
        world.response.then().statusCode(expectedStatus);
    }
}
