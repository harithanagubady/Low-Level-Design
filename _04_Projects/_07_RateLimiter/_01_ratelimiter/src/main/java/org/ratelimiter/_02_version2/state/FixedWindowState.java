package org.ratelimiter._02_version2.state;

public class FixedWindowState extends RateLimitState {

	public long windowStartTimeMillis;
	public int requestCount;

	public FixedWindowState(long now) {
		this.windowStartTimeMillis = now;
		this.requestCount = 0;
	}

}
