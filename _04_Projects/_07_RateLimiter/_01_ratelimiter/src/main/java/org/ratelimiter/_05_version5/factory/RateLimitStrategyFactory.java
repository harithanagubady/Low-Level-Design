package org.ratelimiter._05_version5.factory;

import org.ratelimiter._05_version5.strategy.FixedWindowStrategy;
import org.ratelimiter._05_version5.strategy.LeakyBucketStrategy;
import org.ratelimiter._05_version5.strategy.RateLimitStrategy;
import org.ratelimiter._05_version5.strategy.SlidingWindowLogStrategy;
import org.ratelimiter._05_version5.strategy.SubWindowStrategy;
import org.ratelimiter._05_version5.strategy.TokenBucketStrategy;
import org.ratelimiter._05_version5.strategy.WeightedWindowStrategy;

public class RateLimitStrategyFactory {

	public RateLimitStrategy getStrategy(RateLimitAlgorithm algorithm) {

		switch(algorithm) {

			case FIXED_WINDOW -> {
				return new FixedWindowStrategy();
			}
			case SLIDING_WINDOW_LOG -> {
				return new SlidingWindowLogStrategy();
			}
			case SUB_WINDOW -> {
				return new SubWindowStrategy();
			}
			case WEIGHTED_WINDOW -> {
				return new WeightedWindowStrategy();
			}
			case TOKEN_BUCKET -> {
				return new TokenBucketStrategy();
			}
			case LEAKY_WINDOW -> {
				return new LeakyBucketStrategy();
			}
			default -> throw new IllegalArgumentException("Unsupported algorithm: " + algorithm);
		}
	}
}
