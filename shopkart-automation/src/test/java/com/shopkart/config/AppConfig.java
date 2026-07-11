package com.shopkart.config;

import java.io.InputStream;
import java.util.Properties;

public final class AppConfig {
  private static final Properties VALUES = load();
  private AppConfig() { }

  private static Properties load() {
    var properties = new Properties();
    var profile = System.getProperty("profile", "local");
    try (InputStream stream = AppConfig.class.getResourceAsStream("/config/application-" + profile + ".properties")) {
      if (stream == null) throw new IllegalStateException("Unknown config profile: " + profile);
      properties.load(stream);
      return properties;
    } catch (Exception e) { throw new IllegalStateException("Cannot load test configuration", e); }
  }

  public static String apiUrl() { return override("SHOPKART_API_URL", "api.base-url"); }
  public static String uiUrl() { return override("SHOPKART_UI_URL", "ui.base-url"); }
  public static boolean headless() { return Boolean.parseBoolean(override("SHOPKART_HEADLESS", "headless")); }
  private static String override(String env, String property) { return System.getenv().getOrDefault(env, VALUES.getProperty(property)); }
}
