package com.shopkart.support;
import com.codeborne.selenide.Configuration;
import com.shopkart.config.AppConfig;
import org.junit.jupiter.api.BeforeEach;
public abstract class BaseUiTest { @BeforeEach void browserConfig() { Configuration.browser = "chrome"; Configuration.headless = AppConfig.headless(); Configuration.timeout = 10000; } }
