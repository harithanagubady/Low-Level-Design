package org.ratelimiter._04_version4;

import java.time.Clock;

import org.ratelimiter._04_version4.policy.WindowPolicy;
import org.ratelimiter._04_version4.state.RateLimitState;
import org.ratelimiter._04_version4.store.RateLimitStateStore;
import org.ratelimiter._04_version4.strategy.RateLimitStrategy;

public class RateLimiter {

	private final WindowPolicy rateLimitPolicy;
	private final RateLimitStrategy rateLimitStrategy;
	private final Clock clock;

	private final RateLimitStateStore stateStore;

	public RateLimiter(WindowPolicy rateLimitPolicy, RateLimitStrategy rateLimitStrategy, RateLimitStateStore stateStore, Clock clock) {
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
