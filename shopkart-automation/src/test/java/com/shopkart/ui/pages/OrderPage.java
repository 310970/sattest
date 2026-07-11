package com.shopkart.ui.pages;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$x;
public final class OrderPage { public void statusIs(String status) { $x("//*[@data-field='order-status']").shouldHave(text(status)); } }
