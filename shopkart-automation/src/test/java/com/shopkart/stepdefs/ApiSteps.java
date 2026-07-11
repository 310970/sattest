package com.shopkart.stepdefs;

import com.shopkart.api.ShopKartApi;
import com.shopkart.support.WorldContext;
import io.cucumber.java.en.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

/** Thin glue: behavior lives in ShopKartApi, no credentials are exposed here. */
public final class ApiSteps {
  private final ShopKartApi api = new ShopKartApi();
  private final WorldContext world = new WorldContext();
  private io.restassured.response.Response response;

  @Given("{string} is authenticated") public void authenticated(String persona) { world.token = api.login(persona); }
  @When("she adds {int} x {string} to a new cart") public void addToNewCart(int qty, String sku) {
    world.cartId = api.createCart(world.token).then().statusCode(201).extract().path("cartId");
    response = api.addItem(world.token, world.cartId, sku, qty);
  }
  @Then("the cart total is {int} paise") public void cartTotal(int total) { response.then().statusCode(200); assertEquals(total, response.path("totalPaise")); }
  @When("a shopper searches products for {string}") public void search(String query) { response = api.search(query); }
  @Then("the product results include {string}") public void containsSku(String sku) { response.then().statusCode(200); org.junit.jupiter.api.Assertions.assertTrue(response.path("sku").toString().contains(sku)); }
  @Then("the response status is {int}") public void status(int status) { response.then().statusCode(status); }
}
