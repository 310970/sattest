package com.shopkart.support;

import com.shopkart.api.AuthClient;
import com.shopkart.api.CartClient;
import com.shopkart.api.OrderClient;
import com.shopkart.api.ProductClient;
import org.junit.jupiter.api.BeforeEach;

public abstract class BaseApiTest {
    protected AuthClient authClient;
    protected CartClient cartClient;
    protected OrderClient orderClient;
    protected ProductClient productClient;

    @BeforeEach
    void apiClient() {
        authClient = new AuthClient();
        cartClient = new CartClient();
        orderClient = new OrderClient();
        productClient = new ProductClient();
    }
}
