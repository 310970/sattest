package com.shopkart.ui.components;
import com.shopkart.ui.locators.Xp;
public final class Header { public void openCart() { Xp.button("Cart").click(); } public void signOut() { Xp.button("Sign out").click(); } }
