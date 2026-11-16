package org.ratelimiter._05_version5.strategy;

import org.ratelimiter._05_version5.policy.RateLimitPolicy;
import org.ratelimiter._05_version5.policy.WindowPolicy;
import org.ratelimiter._05_version5.state.RateLimitState;
import org.ratelimiter._05_version5.state.SlidingLogState;

public class SlidingWindowLogStrategy implements RateLimitStrategy {


	@Override
	public RateLimitState createState(RateLimitPolicy policy, long now) {
		return new SlidingLogState();
	}

	@Override
	public boolean allowRequest(RateLimitState state, RateLimitPolicy policy, long now) {

		WindowPolicy windowPolicy = (WindowPolicy) policy;
		long windowStart = now - windowPolicy.getWindowDurationMillis();

		SlidingLogState slidingLogState = (SlidingLogState) state;
		while (!slidingLogState.getRequestTimeStamps().isEmpty() && slidingLogState.getRequestTimeStamps().peekFirst() <= windowStart) {
			slidingLogState.getRequestTimeStamps().pollFirst();
		}

		if (slidingLogState.getRequestTimeStamps().size() >= windowPolicy.getMaxRequests()) {
			return false;
		}

		slidingLogState.getRequestTimeStamps().addLast(now);
		return true;
	}
}
