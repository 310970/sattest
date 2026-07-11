package com.shopkart.data.secret;

import java.io.FileInputStream;
import java.util.Properties;

/** Resolves secrets without logging them: environment first, then git-ignored local file. */
public final class Secrets {
  private static final Properties LOCAL = loadLocal();
  private Secrets() { }
  public static String passwordFor(String persona) { return get(persona + ".password"); }
  public static String get(String key) {
    var envKey = "SHOPKART_" + key.replace('.', '_').toUpperCase();
    var value = System.getenv(envKey);
    if (value == null || value.isBlank()) value = LOCAL.getProperty(key);
    if (value == null || value.isBlank()) throw new IllegalStateException("Missing secret: " + envKey + " (or " + key + " in secrets.local.properties)");
    return value;
  }
  private static Properties loadLocal() {
    var properties = new Properties();
    try (var input = new FileInputStream("secrets.local.properties")) { properties.load(input); }
    catch (Exception ignored) { /* environment-only setup is supported */ }
    return properties;
  }
}
