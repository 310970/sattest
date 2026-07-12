package com.shopkart.ui.pages;

import com.codeborne.selenide.Selenide;
import com.shopkart.config.AppConfig;
import com.shopkart.ui.locators.Xp;

import static com.codeborne.selenide.Selenide.$x;

public final class LoginPage {
  public LoginPage open() { Selenide.open(AppConfig.uiUrl() + "/login"); return this; }
  public void signIn(String email, String password) { $x("//input[@name='email']").setValue(email); $x("//input[@name='password']").setValue(password); Xp.button("Sign in").click(); }
}
