package org.ratelimiter._04_version4.state;

import java.util.ArrayDeque;
import java.util.Deque;

public class SlidingLogState extends RateLimitState {
	Deque<Long> timestamps = new ArrayDeque<>();
}
