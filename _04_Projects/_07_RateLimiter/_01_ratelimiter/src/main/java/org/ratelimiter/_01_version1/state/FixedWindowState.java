package org.ratelimiter._01_version1.state;

public class FixedWindowState extends RateLimitState {

	public long windowStartTimeMillis;
	public int requestCount;

	public FixedWindowState(long now) {
		this.windowStartTimeMillis = now;
		this.requestCount = 0;
	}

}
