package com.shopkart.api;

import com.shopkart.config.AppConfig;
import com.shopkart.data.secret.Secrets;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import java.util.Map;

public final class ShopKartApi {
  public ShopKartApi() { RestAssured.baseURI = AppConfig.apiUrl(); }
  public String login(String persona) {
    var response = RestAssured.given().contentType("application/json")
        .body(Map.of("email", persona + "@shopkart.test", "password", Secrets.passwordFor(persona)))
        .post("/auth/login").then().statusCode(200).extract().response();
    return response.path("token");
  }
  public Response search(String query) { return RestAssured.given().queryParam("q", query).get("/products"); }
  public Response createCart(String token) { return auth(token).post("/carts"); }
  public Response addItem(String token, long cartId, String sku, int qty) { return auth(token).body(Map.of("sku", sku, "qty", qty)).post("/carts/{id}/items", cartId); }
  public Response cart(String token, long cartId) { return auth(token).get("/carts/{id}", cartId); }
  public Response placeOrder(String token, long cartId) { return auth(token).body(Map.of("cartId", cartId, "address", "42 Test Street, Bengaluru 560001")).post("/orders"); }
  public Response order(String token, long orderId) { return auth(token).get("/orders/{id}", orderId); }
  public Response cancel(String token, long orderId) { return auth(token).post("/orders/{id}/cancel", orderId); }
  private io.restassured.specification.RequestSpecification auth(String token) { return RestAssured.given().header("Authorization", "Bearer " + token).contentType("application/json"); }
}
