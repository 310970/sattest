package com.shopkart.support;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import static com.codeborne.selenide.Selenide.closeWebDriver;
/** Lifecycle only; no business behavior or secrets in hooks. */
public final class Hooks { @Before public void beforeScenario() { } @After public void afterScenario() { closeWebDriver(); } }
