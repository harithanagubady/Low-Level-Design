package org.ratelimiter._05_version5.strategy;

import org.ratelimiter._05_version5.policy.RateLimitPolicy;
import org.ratelimiter._05_version5.policy.WindowPolicy;
import org.ratelimiter._05_version5.state.RateLimitState;
import org.ratelimiter._05_version5.state.SlidingWindowWeightedState;

public class WeightedWindowStrategy implements RateLimitStrategy {

	@Override
	public RateLimitState createState(RateLimitPolicy policy, long now) {
		return new SlidingWindowWeightedState(now);
	}

	@Override
	public boolean allowRequest(RateLimitState state, RateLimitPolicy policy, long now) {

		SlidingWindowWeightedState slidingWindowWeightedState = (SlidingWindowWeightedState) state;
		WindowPolicy windowPolicy = (WindowPolicy) policy;
		long windowDuration = windowPolicy.getWindowDurationMillis();

		long elapsedWindows = (now - slidingWindowWeightedState.getWindowStartMillis()) / windowDuration;

		if (elapsedWindows >= 1) {

			if (elapsedWindows == 1) {
				slidingWindowWeightedState.setPreviousWindowCount(slidingWindowWeightedState.getCurrentWindowCount());
			} else {
				slidingWindowWeightedState.setPreviousWindowCount(0);
			}

			slidingWindowWeightedState.setCurrentWindowCount(0);

			slidingWindowWeightedState.setWindowStartMillis(slidingWindowWeightedState.getWindowStartMillis()
					+ elapsedWindows * windowDuration);
		}

		double elapsedFraction = (now - slidingWindowWeightedState.getWindowStartMillis()) / (double)windowDuration;
		double previousWeight = 1.0 - elapsedFraction;

		double estimatedCount = slidingWindowWeightedState.getPreviousWindowCount() * previousWeight
				+ slidingWindowWeightedState.getCurrentWindowCount();

		if (estimatedCount >= windowPolicy.getMaxRequests()) {
			return false;
		}

		slidingWindowWeightedState.setCurrentWindowCount(slidingWindowWeightedState.getCurrentWindowCount() + 1);

 		return true;
	}
}
