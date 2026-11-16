package org.ratelimiter._02_version2;

import java.time.Clock;

import org.ratelimiter._02_version2.state.RateLimitState;
import org.ratelimiter._02_version2.store.RateLimitStateStore;
import org.ratelimiter._02_version2.strategy.RateLimitStrategy;

public class RateLimiter {

	private final RateLimitPolicy rateLimitPolicy;
	private final RateLimitStrategy rateLimitStrategy;
	private final Clock clock;

	private final RateLimitStateStore stateStore;

	public RateLimiter(RateLimitPolicy rateLimitPolicy, RateLimitStrategy rateLimitStrategy, RateLimitStateStore stateStore, Clock clock) {
		this.rateLimitPolicy = rateLimitPolicy;
		this.rateLimitStrategy = rateLimitStrategy;
		this.clock = clock;

		this.stateStore = stateStore;
	}

	public boolean allowRequest(String clientId) {

		long now = clock.millis();
		RateLimitState rateLimitState = stateStore.getOrCreate(clientId, () -> rateLimitStrategy.createState(rateLimitPolicy, now));
		return rateLimitStrategy.allowRequest(rateLimitState, rateLimitPolicy, now);
	}
}
