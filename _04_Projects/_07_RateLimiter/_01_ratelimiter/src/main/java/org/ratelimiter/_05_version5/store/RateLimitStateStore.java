package org.ratelimiter._05_version5.store;

import java.util.function.Supplier;

import org.ratelimiter._05_version5.state.RateLimitState;

//Client-specific state management
public interface RateLimitStateStore {

	RateLimitState getOrCreate(String clientId, Supplier<RateLimitState> stateFactory);
}
