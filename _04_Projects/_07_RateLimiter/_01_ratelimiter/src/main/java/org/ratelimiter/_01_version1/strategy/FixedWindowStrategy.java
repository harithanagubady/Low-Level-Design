package org.ratelimiter._01_version1.strategy;

import org.ratelimiter._01_version1.RateLimitPolicy;
import org.ratelimiter._01_version1.state.FixedWindowState;
import org.ratelimiter._01_version1.state.RateLimitState;

public class FixedWindowStrategy implements RateLimitStrategy {

	@Override
	public RateLimitState createState(RateLimitPolicy policy, long now) {
		return new FixedWindowState(now);
	}

	@Override
	public boolean allowRequest(RateLimitState state, RateLimitPolicy policy, long now) {

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
