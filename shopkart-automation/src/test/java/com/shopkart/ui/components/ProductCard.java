package com.shopkart.ui.components;
import com.shopkart.ui.locators.Xp;
public final class ProductCard { private final String name; public ProductCard(String name) { this.name = name; } public void addToCart() { Xp.addToCart(name).click(); } }
