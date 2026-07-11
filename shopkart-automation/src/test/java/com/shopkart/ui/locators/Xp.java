package com.shopkart.ui.locators;

import static com.codeborne.selenide.Selenide.$x;
import com.codeborne.selenide.SelenideElement;

/** All dynamic XPath templates live here: relative, stable and parameterized. */
public final class Xp {
  public static final String PRODUCT = "//div[contains(@class,'product')][.//*[normalize-space()='%s']]";
  public static final String CART_LINE = "//tr[contains(@class,'cart-line')][td[normalize-space()='%s']]";
  private Xp() { }
  public static SelenideElement product(String name) { return $x(PRODUCT.formatted(name)); }
  public static SelenideElement addToCart(String name) { return product(name).$x(".//button[normalize-space()='Add to cart']"); }
  public static SelenideElement cartLine(String sku) { return $x(CART_LINE.formatted(sku)); }
  public static SelenideElement button(String text) { return $x("//button[normalize-space()='" + text + "']"); }
}
