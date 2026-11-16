package org.ratelimiter._04_version4.state;

public class TokenBucketState extends RateLimitState {

	double availableTokens;
	long lastRefillTimeMillis;

	public TokenBucketState(double availableTokens, long now) {
		this.availableTokens = availableTokens;
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
