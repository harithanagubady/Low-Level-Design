package org.ratelimiter._05_version5.factory;

public enum RateLimitAlgorithm {

	FIXED_WINDOW, SLIDING_WINDOW_LOG, SUB_WINDOW, WEIGHTED_WINDOW, TOKEN_BUCKET, LEAKY_WINDOW;
}
