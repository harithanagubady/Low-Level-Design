package org.ratelimiter._03_version3.store;

import java.util.function.Supplier;

import org.ratelimiter._03_version3.state.RateLimitState;

//Client-specific state management
public interface RateLimitStateStore {

	RateLimitState getOrCreate(String clientId, Supplier<RateLimitState> stateFactory);
}
