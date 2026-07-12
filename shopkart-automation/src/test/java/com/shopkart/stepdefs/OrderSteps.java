package com.shopkart.stepdefs;

import com.shopkart.api.AuthClient;
import com.shopkart.api.OrderClient;
import com.shopkart.data.builders.OrderBuilder;
import com.shopkart.support.WorldContext;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.junit.jupiter.api.Assertions.assertEquals;

public final class OrderSteps {
    private final AuthClient authClient = new AuthClient();
    private final OrderClient orderClient = new OrderClient();
    private final WorldContext world;

    public OrderSteps(WorldContext world) {
        this.world = world;
    }

    public OrderBuilder.CreatedOrder placedOrderFor(String persona) {
        return OrderBuilder.anOrder().forCustomer(persona).build();
    }

    @Given("{string} has a PLACED order")
    public void customerHasPlacedOrder(String persona) {
        var order = placedOrderFor(persona);
        world.token = order.token();
        world.cartId = order.cartId();
        world.orderId = order.orderId();
    }

    @When("she cancels that order twice")
    public void sheCancelsOrderTwice() {
        world.firstStatus = orderClient.cancel(world.token, world.orderId).getStatusCode();
        world.response = orderClient.cancel(world.token, world.orderId);
        world.secondStatus = world.response.getStatusCode();
    }

    @Then("the first response is {int} and the second is {int}")
    public void cancellationResponsesAre(int firstExpected, int secondExpected) {
        assertEquals(firstExpected, world.firstStatus);
        assertEquals(secondExpected, world.secondStatus);
    }

    @When("^\\\"([^\\\"]+)\\\" requests GET /api/orders/\\{that id\\}$")
    public void customerRequestsOrder(String persona) {
        world.response = orderClient.get(authClient.login(persona), world.orderId);
    }

    @When("she checks out with a valid address")
    public void sheChecksOut() {
        world.response = orderClient.place(world.token, world.cartId);
        world.response.then().statusCode(201);
        world.orderId = world.response.path("orderId");
    }

    @Then("the order confirmation shows status {string}")
    public void orderConfirmationShowsStatus(String status) {
        assertEquals(status, world.response.path("status"));
    }

    @Then("^GET /api/orders/\\{id\\} returns PLACED and totalPaise (\\d+)$")
    public void orderApiReturnsPlacedAndTotal(int totalPaise) {
        var order = orderClient.get(world.token, world.orderId);
        order.then().statusCode(200);
        assertEquals("PLACED", order.path("status"));
        int actualTotal = order.path("totalPaise");
        assertEquals(totalPaise, actualTotal);
    }
}
