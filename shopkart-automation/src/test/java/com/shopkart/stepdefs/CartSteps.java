package com.shopkart.stepdefs;
import com.shopkart.data.builders.CartBuilder;
public final class CartSteps { public CartBuilder.BuiltCart cartFor(String persona, String sku, int quantity) { return CartBuilder.aCart().forCustomer(persona).withItem(sku, quantity).build(); } }
