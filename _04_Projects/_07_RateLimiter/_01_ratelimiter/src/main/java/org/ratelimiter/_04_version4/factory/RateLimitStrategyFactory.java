package org.ratelimiter._04_version4.factory;

import org.ratelimiter._04_version4.strategy.FixedWindowStrategy;
import org.ratelimiter._04_version4.strategy.LeakyBucketStrategy;
import org.ratelimiter._04_version4.strategy.RateLimitStrategy;
import org.ratelimiter._04_version4.strategy.SlidingWindowLogStrategy;
import org.ratelimiter._04_version4.strategy.SubWindowStrategy;
import org.ratelimiter._04_version4.strategy.TokenBucketStrategy;
import org.ratelimiter._04_version4.strategy.WeightedWindowStrategy;

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
