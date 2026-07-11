package com.shopkart.ui.pages;
import static com.codeborne.selenide.Selenide.$x;
import com.shopkart.ui.locators.Xp;
public final class CheckoutPage {
  public void placeOrder(String address) { $x("//textarea[@name='address']").setValue(address); Xp.button("Place order").click(); }
}
