package org.ratelimiter._05_version5.policy;

public class WindowPolicy implements RateLimitPolicy {

	private final int maxRequests;
	private final long windowDurationMillis;

	public WindowPolicy(int maxRequests, long windowDurationMillis) {

		if (maxRequests <= 0 || windowDurationMillis <= 0) {
			throw new IllegalArgumentException("Invalid window policy");
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
