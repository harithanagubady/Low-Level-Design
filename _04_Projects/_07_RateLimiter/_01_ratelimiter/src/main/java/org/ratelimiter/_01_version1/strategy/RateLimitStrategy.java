package org.ratelimiter._01_version1.strategy;

import org.ratelimiter._01_version1.RateLimitPolicy;
import org.ratelimiter._01_version1.state.RateLimitState;

public interface RateLimitStrategy {

	RateLimitState createState(RateLimitPolicy policy, long now);
	boolean allowRequest(RateLimitState state, RateLimitPolicy policy, long now);
}
