package org.ratelimiter._02_version2.store;

import java.util.function.Supplier;

import org.ratelimiter._02_version2.state.RateLimitState;

//Client-specific state management
public interface RateLimitStateStore {

	RateLimitState getOrCreate(String clientId, Supplier<RateLimitState> stateFactory);
}
