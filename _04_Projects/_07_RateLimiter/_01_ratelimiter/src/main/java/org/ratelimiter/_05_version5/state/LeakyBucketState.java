package org.ratelimiter._05_version5.state;

import java.util.ArrayDeque;
import java.util.Deque;

public class LeakyBucketState extends RateLimitState {

	private final Deque<Long> scheduledDrainTimes = new ArrayDeque<>();

	public Deque<Long> getScheduledDrainTimes() {
		return scheduledDrainTimes;
	}
}
