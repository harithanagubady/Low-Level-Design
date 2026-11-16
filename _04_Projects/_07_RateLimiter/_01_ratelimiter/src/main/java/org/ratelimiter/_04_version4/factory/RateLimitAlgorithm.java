package org.ratelimiter._04_version4.factory;

public enum RateLimitAlgorithm {

	FIXED_WINDOW, SLIDING_WINDOW_LOG, SUB_WINDOW, WEIGHTED_WINDOW, TOKEN_BUCKET, LEAKY_WINDOW;
}
