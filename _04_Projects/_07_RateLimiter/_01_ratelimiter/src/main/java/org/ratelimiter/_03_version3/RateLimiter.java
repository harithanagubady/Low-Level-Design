package org.ratelimiter._03_version3;

import java.time.Clock;

import org.ratelimiter._03_version3.state.RateLimitState;
import org.ratelimiter._03_version3.store.RateLimitStateStore;
import org.ratelimiter._03_version3.strategy.RateLimitStrategy;

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
		synchronized (rateLimitState){
			return rateLimitStrategy.allowRequest(rateLimitState, rateLimitPolicy, now);
		}
	}
}
