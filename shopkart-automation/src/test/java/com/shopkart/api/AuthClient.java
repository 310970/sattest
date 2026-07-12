package com.shopkart.api;

import com.shopkart.config.AppConfig;
import io.restassured.RestAssured;
import io.restassured.response.Response;

import java.util.Map;

/** REST Assured client for authentication endpoints. */
public class AuthClient {

    public AuthClient() {
        RestAssured.baseURI = AppConfig.apiUrl();
    }

    public String login(String persona) {

        String password;

        switch (persona.toLowerCase()) {
            case "alice":
                password = "alice-dev-pass";
                break;
            case "bob":
                password = "bob-dev-pass";
                break;
            case "carol":
                password = "carol-dev-pass";
                break;
            default:
                throw new IllegalArgumentException("Unknown persona: " + persona);
        }

        return loginWithEmail(persona + "@shopkart.test", password)
                .then()
                .statusCode(200)
                .extract()
                .path("token");
    }

    public Response loginWithEmail(String email, String password) {
        return RestAssured.given()
                .contentType("application/json")
                .body(Map.of(
                        "email", email,
                        "password", password
                ))
                .post("/auth/login");
    }
}