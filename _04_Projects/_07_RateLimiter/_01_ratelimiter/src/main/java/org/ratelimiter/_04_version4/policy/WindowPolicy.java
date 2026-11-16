package org.ratelimiter._04_version4.policy;

public class WindowPolicy implements RateLimitPolicy {

	private final int maxRequests;
	private final long windowDurationMillis;

	public WindowPolicy(int maxRequests, long windowDurationMillis) {

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
