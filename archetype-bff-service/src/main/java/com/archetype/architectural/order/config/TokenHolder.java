package com.archetype.architectural.order.config;

import java.util.concurrent.atomic.AtomicReference;

import org.springframework.stereotype.Component;

@Component
public class TokenHolder {
    private final AtomicReference<String> token = new AtomicReference<>();

    public void setToken(String newToken) {
        this.token.set(newToken);
    }

    public String getToken() {
        return this.token.get();
    }

    public boolean hasToken() {
        return this.token.get() != null && !this.token.get().isEmpty();
    }
}
