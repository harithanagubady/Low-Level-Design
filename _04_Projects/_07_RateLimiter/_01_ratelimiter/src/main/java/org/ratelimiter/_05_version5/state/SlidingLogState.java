package org.ratelimiter._05_version5.state;

import java.util.ArrayDeque;
import java.util.Deque;

public class SlidingLogState extends RateLimitState {
	private final Deque<Long> requestTimeStamps = new ArrayDeque<>();

	public Deque<Long> getRequestTimeStamps() {
		return requestTimeStamps;
	}
}
