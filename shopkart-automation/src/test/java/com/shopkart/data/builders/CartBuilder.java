package com.shopkart.data.builders;

import com.shopkart.api.AuthClient;
import com.shopkart.api.CartClient;

public final class CartBuilder {
  private String persona = "alice", sku = "SKU-BAG"; private int qty = 1;
  public static CartBuilder aCart() { return new CartBuilder(); }
  public CartBuilder forCustomer(String value) { persona = value; return this; }
  public CartBuilder withItem(String value, int quantity) { sku = value; qty = quantity; return this; }
  public BuiltCart build() {
    var authClient = new AuthClient();
    var cartClient = new CartClient();
    var token = authClient.login(persona);
    long id = cartClient.create(token).then().statusCode(201).extract().path("cartId");
    cartClient.addItem(token, id, sku, qty).then().statusCode(200);
    return new BuiltCart(token, id);
  }
  public record BuiltCart(String token, long cartId) { }
}
