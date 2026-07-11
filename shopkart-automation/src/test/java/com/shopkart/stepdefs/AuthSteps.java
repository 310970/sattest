package com.shopkart.stepdefs;
import com.shopkart.api.ShopKartApi;
/** Authentication behavior is centralized in ShopKartApi; steps never receive passwords. */
public final class AuthSteps { private final ShopKartApi api = new ShopKartApi(); public String tokenFor(String persona) { return api.login(persona); } }
