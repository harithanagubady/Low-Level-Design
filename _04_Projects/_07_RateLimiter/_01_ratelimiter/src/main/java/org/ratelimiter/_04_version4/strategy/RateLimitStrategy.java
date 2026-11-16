package org.ratelimiter._04_version4.strategy;

import org.ratelimiter._04_version4.policy.WindowPolicy;
import org.ratelimiter._04_version4.state.RateLimitState;

public interface RateLimitStrategy {

	RateLimitState createState(WindowPolicy policy, long now);
	boolean allowRequest(RateLimitState state, WindowPolicy policy, long now);
}
