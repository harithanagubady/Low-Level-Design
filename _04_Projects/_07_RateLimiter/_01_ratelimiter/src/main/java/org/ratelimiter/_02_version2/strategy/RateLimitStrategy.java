package org.ratelimiter._02_version2.strategy;

import org.ratelimiter._02_version2.RateLimitPolicy;
import org.ratelimiter._02_version2.state.RateLimitState;

public interface RateLimitStrategy {

	RateLimitState createState(RateLimitPolicy policy, long now);
	boolean allowRequest(RateLimitState state, RateLimitPolicy policy, long now);
}
