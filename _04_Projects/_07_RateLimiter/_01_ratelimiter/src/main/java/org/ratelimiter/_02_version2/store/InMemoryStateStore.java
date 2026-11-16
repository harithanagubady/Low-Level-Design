package org.ratelimiter._02_version2.store;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

import org.ratelimiter._02_version2.state.RateLimitState;

public class InMemoryStateStore implements RateLimitStateStore {

	private final Map<String, RateLimitState> states = new HashMap<>();

	@Override
	public RateLimitState getOrCreate(String clientId, Supplier<RateLimitState> stateFactory) {
		return states.computeIfAbsent(clientId, state -> stateFactory.get());
	}
}
