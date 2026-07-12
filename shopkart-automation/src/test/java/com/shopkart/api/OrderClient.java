package com.shopkart.api;

import com.shopkart.config.AppConfig;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.Map;

/** REST Assured client for order endpoints. */
public class OrderClient {
    public OrderClient() {
        RestAssured.baseURI = AppConfig.apiUrl();
    }

    public Response place(String token, long cartId) {
        return authorized(token)
                .body(Map.of("cartId", cartId, "address", "42 Test Street, Bengaluru 560001"))
                .post("/orders");
    }

    public Response get(String token, long orderId) {
        return authorized(token).get("/orders/{id}", orderId);
    }

    public Response cancel(String token, long orderId) {
        return authorized(token).post("/orders/{id}/cancel", orderId);
    }

    private RequestSpecification authorized(String token) {
        return RestAssured.given()
                .header("Authorization", "Bearer " + token)
                .contentType("application/json");
    }
}
