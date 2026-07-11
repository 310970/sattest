# ShopKart automation

Java 21, Cucumber, JUnit Platform, REST Assured, Selenide, Testcontainers, Flyway and Allure framework for the Week-6 pre-capstone.

## Local setup

1. Keep ShopKart API running at `http://localhost:8080` and the UI at `http://localhost:5173`.
2. Copy `src/test/resources/secrets.local.properties.example` to `secrets.local.properties` in the project root, then fill the three passwords from the ShopKart `.env`. This file is ignored by Git.
3. Run `gradle test` (or generate a Gradle wrapper first with a locally installed Gradle).
4. Run `gradle allureServe` to view the report.

Passwords and bearer tokens are never written to feature files, console logs, or Allure attachments.

> API/UI tests target the running SUT. `PostgresSupport` is provided for isolated data-layer tests; point a containerised SUT at its JDBC URL when running the full cold-start suite.
