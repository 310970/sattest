package com.shopkart.support;

import io.restassured.response.Response;

/** Scenario-scoped state shared by thin Cucumber step definitions. */
public final class WorldContext {
    public String token;
    public long cartId;
    public long orderId;
    public Response response;
    public int firstStatus;
    public int secondStatus;
}
