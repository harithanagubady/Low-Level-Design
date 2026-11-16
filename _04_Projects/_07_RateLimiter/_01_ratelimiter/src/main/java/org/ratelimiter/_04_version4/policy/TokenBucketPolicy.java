package org.ratelimiter._04_version4.policy;

public class TokenBucketPolicy implements RateLimitPolicy {

	private final int capacity;
	private final int refillTokens;
	private final long refillPeriodMillis;


	public TokenBucketPolicy(int capacity, int refillTokens, long refillPeriodMillis) {
		this.capacity = capacity;
		this.refillTokens = refillTokens;
		this.refillPeriodMillis = refillPeriodMillis;
	}
}
