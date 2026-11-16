package org.ratelimiter._04_version4.policy;

public class LeakyBucketPolicy implements RateLimitPolicy {

	private final int capacity;
	private final long leakIntervalMillis;


	public LeakyBucketPolicy(int capacity, long leakIntervalMillis) {
		this.capacity = capacity;
		this.leakIntervalMillis = leakIntervalMillis;
	}
}
