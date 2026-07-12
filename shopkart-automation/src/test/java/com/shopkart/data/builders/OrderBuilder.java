package com.shopkart.data.builders;

import com.shopkart.api.AuthClient;
import com.shopkart.api.CartClient;
import com.shopkart.api.OrderClient;

/** Creates only the data a scenario needs through the public API. */
public final class OrderBuilder {
  private final AuthClient authClient = new AuthClient();
  private final CartClient cartClient = new CartClient();
  private final OrderClient orderClient = new OrderClient();
  private String persona = "alice";
  private String sku = "SKU-BAG";
  private int quantity = 1;
  public static OrderBuilder anOrder() { return new OrderBuilder(); }
  public OrderBuilder forCustomer(String value) { persona = value; return this; }
  public OrderBuilder withItem(String value, int qty) { sku = value; quantity = qty; return this; }
  public CreatedOrder build() {
    var token = authClient.login(persona);
    long cartId = cartClient.create(token).then().statusCode(201).extract().path("cartId");
    cartClient.addItem(token, cartId, sku, quantity).then().statusCode(200);
    long orderId = orderClient.place(token, cartId).then().statusCode(201).extract().path("orderId");
    return new CreatedOrder(token, cartId, orderId);
  }
  public record CreatedOrder(String token, long cartId, long orderId) { }
}
