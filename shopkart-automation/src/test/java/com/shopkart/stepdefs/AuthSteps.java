package com.shopkart.stepdefs;

import com.shopkart.api.AuthClient;
import com.shopkart.support.WorldContext;
import io.cucumber.java.en.Given;

/** Authentication steps resolve credentials inside the API client. */
public final class AuthSteps {
    private final AuthClient authClient = new AuthClient();
    private final WorldContext world;

    public AuthSteps(WorldContext world) {
        this.world = world;
    }

    public String tokenFor(String persona) {
        return authClient.login(persona);
    }

    @Given("{string} is authenticated")
    @Given("{string} is logged in")
    public void customerIsAuthenticated(String persona) {
        world.token = tokenFor(persona);
    }
}
