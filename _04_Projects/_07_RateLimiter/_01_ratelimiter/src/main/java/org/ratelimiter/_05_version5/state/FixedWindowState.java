package org.ratelimiter._05_version5.state;

public class FixedWindowState extends RateLimitState {

	public long windowStartTimeMillis;
	public int requestCount;

	public FixedWindowState(long now) {
		this.windowStartTimeMillis = now;
		this.requestCount = 0;
	}

}
