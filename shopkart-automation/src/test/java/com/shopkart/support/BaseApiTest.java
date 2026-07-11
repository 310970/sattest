package com.shopkart.support;
import com.shopkart.api.ShopKartApi;
import org.junit.jupiter.api.BeforeEach;
public abstract class BaseApiTest { protected ShopKartApi api; @BeforeEach void apiClient() { api = new ShopKartApi(); } }
