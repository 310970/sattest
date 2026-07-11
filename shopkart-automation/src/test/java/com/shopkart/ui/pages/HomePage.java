package com.shopkart.ui.pages;
import static com.codeborne.selenide.Selenide.$x;
import com.shopkart.ui.locators.Xp;
public final class HomePage {
  public HomePage search(String query) { $x("//input[@name='q']").setValue(query).pressEnter(); return this; }
  public HomePage add(String productName) { Xp.addToCart(productName).click(); return this; }
  public void openProduct(String productName) { Xp.product(productName).$x(".//h2//button").click(); }
}
