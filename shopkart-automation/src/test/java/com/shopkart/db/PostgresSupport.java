package com.shopkart.db;
import org.testcontainers.containers.PostgreSQLContainer;
/** Per-class isolated PostgreSQL; apply Flyway migrations before each test class. */
public final class PostgresSupport {
  public static final PostgreSQLContainer<?> DATABASE = new PostgreSQLContainer<>("postgres:16-alpine").withDatabaseName("shopkart").withUsername("shopkart").withPassword("shopkart");
  private PostgresSupport() { }
}
