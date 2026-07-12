package com.shopkart.api;

import com.shopkart.config.AppConfig;
import io.restassured.RestAssured;
import io.restassured.response.Response;

/** REST Assured client for product endpoints. */
public class ProductClient {
    public ProductClient() {
        RestAssured.baseURI = AppConfig.apiUrl();
    }

    public Response search(String query) {
        return RestAssured.given().queryParam("q", query).get("/products");
    }
}
