package org.ratelimiter._05_version5.strategy;

import org.ratelimiter._05_version5.policy.RateLimitPolicy;
import org.ratelimiter._05_version5.state.RateLimitState;

public interface RateLimitStrategy {

	RateLimitState createState(RateLimitPolicy policy, long now);
	boolean allowRequest(RateLimitState state, RateLimitPolicy policy, long now);
}
