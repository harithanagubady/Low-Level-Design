package org.ratelimiter._05_version5.strategy;

import java.util.Deque;

import org.ratelimiter._05_version5.policy.LeakyBucketPolicy;
import org.ratelimiter._05_version5.policy.RateLimitPolicy;
import org.ratelimiter._05_version5.state.LeakyBucketState;
import org.ratelimiter._05_version5.state.RateLimitState;

public class LeakyBucketStrategy implements RateLimitStrategy {

	@Override
	public RateLimitState createState(RateLimitPolicy policy, long now) {
		return new LeakyBucketState();
	}

	@Override
	public boolean allowRequest(RateLimitState state, RateLimitPolicy policy, long now) {

		LeakyBucketState leakyBucketState = (LeakyBucketState) state;
		LeakyBucketPolicy leakyBucketPolicy = (LeakyBucketPolicy) policy;
		Deque<Long> drainTimes = leakyBucketState.getScheduledDrainTimes();

		//Remove requests that have already drained
		while (!drainTimes.isEmpty() && drainTimes.peekFirst() <= now) {
			drainTimes.pollFirst();
		}

		//Check whether the bucket is full
		if (drainTimes.size() >= leakyBucketPolicy.getCapacity()) {
			return  false;
		}

		long nextDrainTime = now;

		//schedule next request based on latest time, we must not schedule before the last queued scheduled drain time
		if (!drainTimes.isEmpty()) {
			nextDrainTime = Math.max(now, drainTimes.peekLast());
		}

		nextDrainTime += leakyBucketPolicy.getLeakIntervalMillis();

		drainTimes.addLast(nextDrainTime);

		return true;
	}
}
