package com.shopkart.db;
import org.flywaydb.core.Flyway;
public final class FlywaySupport { private FlywaySupport() { } public static void migrate() { Flyway.configure().dataSource(PostgresSupport.DATABASE.getJdbcUrl(), PostgresSupport.DATABASE.getUsername(), PostgresSupport.DATABASE.getPassword()).locations("classpath:db/migration").load().migrate(); } }
