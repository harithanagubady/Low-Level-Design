package org.ratelimiter._05_version5.strategy;

import org.ratelimiter._05_version5.policy.RateLimitPolicy;
import org.ratelimiter._05_version5.policy.WindowPolicy;
import org.ratelimiter._05_version5.state.FixedWindowState;
import org.ratelimiter._05_version5.state.RateLimitState;

public class FixedWindowStrategy implements RateLimitStrategy {

	@Override
	public RateLimitState createState(RateLimitPolicy policy, long now) {
		return new FixedWindowState(now);
	}

	@Override
	public boolean allowRequest(RateLimitState state, RateLimitPolicy policy, long now) {

		FixedWindowState fixedWindowState = (FixedWindowState) state;
		WindowPolicy windowPolicy = (WindowPolicy) policy;

		if (now - fixedWindowState.windowStartTimeMillis >= windowPolicy.getWindowDurationMillis()) {
			fixedWindowState.windowStartTimeMillis = now;
			fixedWindowState.requestCount = 0;
		}

		if (fixedWindowState.requestCount >= windowPolicy.getMaxRequests()) {
			return false;
		}

		fixedWindowState.requestCount++;
		return true;
	}
}
