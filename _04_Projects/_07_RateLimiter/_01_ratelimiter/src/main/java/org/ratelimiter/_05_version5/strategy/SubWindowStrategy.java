package org.ratelimiter._05_version5.strategy;

import org.ratelimiter._05_version5.policy.RateLimitPolicy;
import org.ratelimiter._05_version5.policy.SubWindowPolicy;
import org.ratelimiter._05_version5.state.RateLimitState;
import org.ratelimiter._05_version5.state.SubWindowState;

public class SubWindowStrategy implements RateLimitStrategy {

	@Override
	public RateLimitState createState(RateLimitPolicy policy, long now) {
		SubWindowPolicy subWindowPolicy = (SubWindowPolicy) policy;
		return new SubWindowState(subWindowPolicy.getNumberOfBuckets());
	}

	@Override
	public boolean allowRequest(RateLimitState state, RateLimitPolicy policy, long now) {

		SubWindowPolicy subWindowPolicy = (SubWindowPolicy) policy;
		long bucketDuration = subWindowPolicy.getBucketDurationMillis();
		int numberOfBuckets = subWindowPolicy.getNumberOfBuckets();

		long currentBucketId = now / bucketDuration;

		int currentIndex = Math.floorMod(currentBucketId, numberOfBuckets);

		SubWindowState subWindowState = (SubWindowState) state;

		if(subWindowState.getBucketIds()[currentIndex] != currentBucketId) {
			subWindowState.getBucketIds()[currentIndex] = currentBucketId;
			subWindowState.getBucketCounts()[currentIndex] = 0;
		}

		long oldestBucketId = currentBucketId - numberOfBuckets + 1;

		int totalRequests = 0;

		for (int i = 0; i < numberOfBuckets; i++) {

			long bucketId = subWindowState.getBucketIds()[i];

			if(bucketId >= oldestBucketId && bucketId <= currentBucketId) {
				totalRequests += subWindowState.getBucketCounts()[i];
			}
		}

		if(totalRequests >= subWindowPolicy.getMaxRequests()) return false;

		subWindowState.getBucketCounts()[currentIndex]++;

		return true;
	}
}
