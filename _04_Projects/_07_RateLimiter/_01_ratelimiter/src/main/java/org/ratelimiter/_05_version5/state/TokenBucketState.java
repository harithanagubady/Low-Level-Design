package org.ratelimiter._05_version5.state;

public class TokenBucketState extends RateLimitState {

	private double availableTokens;
	private long lastRefillTimeMillis;

	public TokenBucketState(int capacity, long now) {
		this.availableTokens = capacity;
		this.lastRefillTimeMillis = now;
	}

	public double getAvailableTokens() {
		return availableTokens;
	}

	public void setAvailableTokens(double availableTokens) {
		this.availableTokens = availableTokens;
	}

	public long getLastRefillTimeMillis() {
		return lastRefillTimeMillis;
	}

	public void setLastRefillTimeMillis(long lastRefillTimeMillis) {
		this.lastRefillTimeMillis = lastRefillTimeMillis;
	}
}
