package com.shopkart.data.builders;

import com.shopkart.api.ShopKartApi;

/** Creates only the data a scenario needs through the public API. */
public final class OrderBuilder {
  private final ShopKartApi api = new ShopKartApi();
  private String persona = "alice";
  private String sku = "SKU-BAG";
  private int quantity = 1;
  public static OrderBuilder anOrder() { return new OrderBuilder(); }
  public OrderBuilder forCustomer(String value) { persona = value; return this; }
  public OrderBuilder withItem(String value, int qty) { sku = value; quantity = qty; return this; }
  public CreatedOrder build() {
    var token = api.login(persona);
    long cartId = api.createCart(token).then().statusCode(201).extract().path("cartId");
    api.addItem(token, cartId, sku, quantity).then().statusCode(200);
    long orderId = api.placeOrder(token, cartId).then().statusCode(201).extract().path("orderId");
    return new CreatedOrder(token, cartId, orderId);
  }
  public record CreatedOrder(String token, long cartId, long orderId) { }
}
