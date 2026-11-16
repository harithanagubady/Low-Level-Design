package org.ratelimiter._05_version5.policy;

public class LeakyBucketPolicy implements RateLimitPolicy {

	private final int capacity;
	private final long leakIntervalMillis;


	public LeakyBucketPolicy(int capacity, long leakIntervalMillis) {

		if (capacity <= 0 || leakIntervalMillis <= 0) {
			throw new IllegalArgumentException("Invalid leaky bucket policy");
		}
		this.capacity = capacity;
		this.leakIntervalMillis = leakIntervalMillis;
	}

	public int getCapacity() {
		return capacity;
	}

	public long getLeakIntervalMillis() {
		return leakIntervalMillis;
	}
}
