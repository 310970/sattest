package com.shopkart.data.builders;
import com.shopkart.api.ShopKartApi;
public final class CartBuilder {
  private String persona = "alice", sku = "SKU-BAG"; private int qty = 1;
  public static CartBuilder aCart() { return new CartBuilder(); }
  public CartBuilder forCustomer(String value) { persona = value; return this; }
  public CartBuilder withItem(String value, int quantity) { sku = value; qty = quantity; return this; }
  public BuiltCart build() { var api = new ShopKartApi(); var token = api.login(persona); long id = api.createCart(token).then().statusCode(201).extract().path("cartId"); api.addItem(token, id, sku, qty).then().statusCode(200); return new BuiltCart(token, id); }
  public record BuiltCart(String token, long cartId) { }
}
