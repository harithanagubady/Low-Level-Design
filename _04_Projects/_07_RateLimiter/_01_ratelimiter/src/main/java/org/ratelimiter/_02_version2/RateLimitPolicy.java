package org.ratelimiter._02_version2;

public class RateLimitPolicy {

	private final int maxRequests;
	private final long windowDurationMillis;

	public RateLimitPolicy(int maxRequests, long windowDurationMillis) {

		if (maxRequests <= 0 || windowDurationMillis <= 0) {
			throw new IllegalArgumentException("Invalid rate limit policy");
		}
		this.maxRequests = maxRequests;
		this.windowDurationMillis = windowDurationMillis;
	}

	public long getWindowDurationMillis() {
		return windowDurationMillis;
	}

	public int getMaxRequests() {
		return maxRequests;
	}
}
