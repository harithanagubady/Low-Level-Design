package org.ratelimiter._04_version4.state;

import java.util.ArrayDeque;
import java.util.Deque;

public class LeakyBucketState extends RateLimitState {

	private final Deque<Long> scheduledDrainTimes = new ArrayDeque<>();

	public Deque<Long> getScheduledDrainTimes() {
		return scheduledDrainTimes;
	}
}
