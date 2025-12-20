package com.example.dbos.quarkus.poc;

import jakarta.enterprise.context.RequestScoped;

@RequestScoped
public class TenantContext {

    private static final ThreadLocal<String> CURRENT_TENANT = new ThreadLocal<>();

    private TenantContext() {
    }

    public static void set(String tenantId) {
        CURRENT_TENANT.set(tenantId);
    }

    public static String get() {
        return CURRENT_TENANT.get();
    }

    public static void clear() {
        CURRENT_TENANT.remove();
    }

    // Optional: helper to use with try-with-resources
    public static AutoCloseable use(String tenantId) {
        set(tenantId);
        return TenantContext::clear;
    }
}