package org.ratelimiter._04_version4.store;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.function.Supplier;

import org.ratelimiter._04_version4.state.RateLimitState;

public class InMemoryStateStore implements RateLimitStateStore {

	private final ConcurrentMap<String, RateLimitState> states = new ConcurrentHashMap<>();

	@Override
	public RateLimitState getOrCreate(String clientId, Supplier<RateLimitState> stateFactory) {
		return states.computeIfAbsent(clientId, state -> stateFactory.get());
	}
}
