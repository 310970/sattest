package com.shopkart.ui.pages;
import static com.codeborne.selenide.Condition.text;
import com.shopkart.ui.locators.Xp;
public final class CartPage {
  public CartPage totalShows(String value) { com.codeborne.selenide.Selenide.$x("//*[@data-role='cart-total']").shouldHave(text(value)); return this; }
  public CartPage lineTotal(String sku, String amount) { Xp.cartLine(sku).$x(".//td[contains(@class,'line-total')]").shouldHave(text(amount)); return this; }
  public void checkout() { Xp.button("Checkout").click(); }
}
