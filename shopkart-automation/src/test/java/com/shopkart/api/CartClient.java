package com.shopkart.api;

import com.shopkart.config.AppConfig;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.Map;

/** REST Assured client for cart endpoints. */
public class CartClient {
    public CartClient() {
        RestAssured.baseURI = AppConfig.apiUrl();
    }

    public Response create(String token) {
        return authorized(token).post("/carts");
    }

    public Response addItem(String token, long cartId, String sku, int quantity) {
        return authorized(token)
                .body(Map.of("sku", sku, "qty", quantity))
                .post("/carts/{id}/items", cartId);
    }

    public Response get(String token, long cartId) {
        return authorized(token).get("/carts/{id}", cartId);
    }

    private RequestSpecification authorized(String token) {
        return RestAssured.given()
                .header("Authorization", "Bearer " + token)
                .contentType("application/json");
    }
}
