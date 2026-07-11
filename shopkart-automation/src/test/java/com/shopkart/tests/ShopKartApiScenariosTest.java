package com.shopkart.tests;

import com.shopkart.api.ShopKartApi;
import com.shopkart.data.builders.OrderBuilder;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/** The API implementations of the six gate scenarios. Credentials come only from Secrets. */
class ShopKartApiScenariosTest {
  private final ShopKartApi api = new ShopKartApi();

  @Test void productSearchReturnsMatchingProducts() {
    api.search("bag").then().statusCode(200).body("sku", org.hamcrest.Matchers.hasItem("SKU-BAG"));
  }

  @Test void cartTotalIsPaiseSafe() {
    var token = api.login("alice");
    long cartId = api.createCart(token).then().statusCode(201).extract().path("cartId");
    api.addItem(token, cartId, "SKU-BAG", 2).then().statusCode(200).body("totalPaise", org.hamcrest.Matchers.is(99800));
  }

  @Test void checkoutCreatesPlacedOrderWithCorrectTotal() {
    var created = OrderBuilder.anOrder().forCustomer("alice").withItem("SKU-BAG", 2).build();
    api.order(created.token(), created.orderId()).then().statusCode(200)
        .body("status", org.hamcrest.Matchers.is("PLACED"))
        .body("totalPaise", org.hamcrest.Matchers.is(99800));
  }

  @Test void anotherCustomerCannotReadAnOrder() {
    var created = OrderBuilder.anOrder().forCustomer("alice").build();
    api.order(api.login("bob"), created.orderId()).then().statusCode(403);
  }

  @Test void placedOrderCanOnlyBeCancelledOnce() {
    var created = OrderBuilder.anOrder().forCustomer("alice").build();
    api.cancel(created.token(), created.orderId()).then().statusCode(200).body("status", org.hamcrest.Matchers.is("CANCELLED"));
    api.cancel(created.token(), created.orderId()).then().statusCode(409);
  }

  @Test void quantityBeyondStockIsRejected() {
    var token = api.login("alice");
    long cartId = api.createCart(token).then().statusCode(201).extract().path("cartId");
    api.addItem(token, cartId, "SKU-BAG", 11).then().statusCode(409);
  }
}
