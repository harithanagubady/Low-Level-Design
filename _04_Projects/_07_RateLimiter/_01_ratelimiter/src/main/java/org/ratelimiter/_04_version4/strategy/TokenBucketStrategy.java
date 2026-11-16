package org.ratelimiter._04_version4.strategy;

import org.ratelimiter._04_version4.policy.WindowPolicy;
import org.ratelimiter._04_version4.state.FixedWindowState;
import org.ratelimiter._04_version4.state.RateLimitState;

public class TokenBucketStrategy implements RateLimitStrategy {

	@Override
	public RateLimitState createState(WindowPolicy policy, long now) {
		return new FixedWindowState(now);
	}

	@Override
	public boolean allowRequest(RateLimitState state, WindowPolicy policy, long now) {

		FixedWindowState fixedWindowState = (FixedWindowState) state;

		if (now - fixedWindowState.windowStartTimeMillis >= policy.getWindowDurationMillis()) {
			fixedWindowState.windowStartTimeMillis = now;
			fixedWindowState.requestCount = 0;
		}

		if (fixedWindowState.requestCount >= policy.getMaxRequests()) {
			return false;
		}

		fixedWindowState.requestCount++;
		return true;
	}
}
