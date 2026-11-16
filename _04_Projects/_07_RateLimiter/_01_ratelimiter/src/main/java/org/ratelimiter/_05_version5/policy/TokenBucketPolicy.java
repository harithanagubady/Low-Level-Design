package org.ratelimiter._05_version5.policy;

public class TokenBucketPolicy implements RateLimitPolicy {

	private final int capacity;
	private final int refillTokens;
	private final long refillPeriodMillis;


	public TokenBucketPolicy(int capacity, int refillTokens, long refillPeriodMillis) {

		if (capacity <= 0 || refillTokens <= 0 || refillPeriodMillis <= 0) {
			throw new IllegalArgumentException("Invalid token policy");
		}

		this.capacity = capacity;
		this.refillTokens = refillTokens;
		this.refillPeriodMillis = refillPeriodMillis;
	}

	public int getCapacity() {
		return capacity;
	}

	public int getRefillTokens() {
		return refillTokens;
	}

	public long getRefillPeriodMillis() {
		return refillPeriodMillis;
	}
}
