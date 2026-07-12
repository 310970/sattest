package com.shopkart.stepdefs;

import com.shopkart.api.AuthClient;

/** Authentication steps resolve credentials inside the API client. */
public final class AuthSteps {
    private final AuthClient authClient = new AuthClient();

    public String tokenFor(String persona) {
        return authClient.login(persona);
    }
}
