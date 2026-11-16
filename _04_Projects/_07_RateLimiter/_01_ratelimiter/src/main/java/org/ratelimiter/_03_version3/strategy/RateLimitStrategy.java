package org.ratelimiter._03_version3.strategy;

import org.ratelimiter._03_version3.RateLimitPolicy;
import org.ratelimiter._03_version3.state.RateLimitState;

public interface RateLimitStrategy {

	RateLimitState createState(RateLimitPolicy policy, long now);
	boolean allowRequest(RateLimitState state, RateLimitPolicy policy, long now);
}
