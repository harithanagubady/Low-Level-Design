package org.ratelimiter._01_version1;

import java.time.Clock;

import org.ratelimiter._01_version1.state.RateLimitState;
import org.ratelimiter._01_version1.strategy.RateLimitStrategy;

public class RateLimiter {

	private final RateLimitPolicy rateLimitPolicy;
	private final RateLimitStrategy rateLimitStrategy;
	private final Clock clock;

	private final RateLimitState rateLimitState;

	public RateLimiter(RateLimitPolicy rateLimitPolicy, RateLimitStrategy rateLimitStrategy, Clock clock) {
		this.rateLimitPolicy = rateLimitPolicy;
		this.rateLimitStrategy = rateLimitStrategy;
		this.clock = clock;

		this.rateLimitState = rateLimitStrategy.createState(rateLimitPolicy, clock.millis());
	}

	public boolean allowRequest() {
		return rateLimitStrategy.allowRequest(rateLimitState, rateLimitPolicy, clock.millis());
	}
}
