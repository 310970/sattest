package com.shopkart.api;

import com.shopkart.config.AppConfig;
import com.shopkart.data.secret.Secrets;
import io.restassured.RestAssured;
import io.restassured.response.Response;

import java.util.Map;

/** REST Assured client for authentication endpoints. */
public class AuthClient {
    public AuthClient() {
        RestAssured.baseURI = AppConfig.apiUrl();
    }

    public String login(String persona) {
        return loginWithEmail(persona + "@shopkart.test", Secrets.passwordFor(persona))
                .then()
                .statusCode(200)
                .extract()
                .path("token");
    }

    public Response loginWithEmail(String email, String password) {
        return RestAssured.given()
                .contentType("application/json")
                .body(Map.of("email", email, "password", password))
                .post("/auth/login");
    }
}
