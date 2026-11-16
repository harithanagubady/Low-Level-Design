package org.ratelimiter._05_version5.state;

public class SlidingWindowWeightedState extends RateLimitState {

	private long windowStartMillis;
	private int previousWindowCount;
	private int currentWindowCount;

	public SlidingWindowWeightedState(long now) {
		this.windowStartMillis = now;
		this.previousWindowCount = 0;
		this.currentWindowCount = 0;
	}

	public long getWindowStartMillis() {
		return windowStartMillis;
	}

	public void setWindowStartMillis(long windowStartMillis) {
		this.windowStartMillis = windowStartMillis;
	}

	public int getPreviousWindowCount() {
		return previousWindowCount;
	}

	public void setPreviousWindowCount(int previousWindowCount) {
		this.previousWindowCount = previousWindowCount;
	}

	public int getCurrentWindowCount() {
		return currentWindowCount;
	}

	public void setCurrentWindowCount(int currentWindowCount) {
		this.currentWindowCount = currentWindowCount;
	}
}
