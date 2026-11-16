package org.ratelimiter._04_version4.store;

import java.util.function.Supplier;

import org.ratelimiter._04_version4.state.RateLimitState;

//Client-specific state management
public interface RateLimitStateStore {

	RateLimitState getOrCreate(String clientId, Supplier<RateLimitState> stateFactory);
}
