package com.shopkart.stepdefs;
import com.shopkart.data.builders.OrderBuilder;
public final class OrderSteps { public OrderBuilder.CreatedOrder placedOrderFor(String persona) { return OrderBuilder.anOrder().forCustomer(persona).build(); } }
