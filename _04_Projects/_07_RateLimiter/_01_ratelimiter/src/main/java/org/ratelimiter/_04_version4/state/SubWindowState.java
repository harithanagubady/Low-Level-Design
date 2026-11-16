package org.ratelimiter._04_version4.state;

import java.util.Arrays;

public class SubWindowState extends RateLimitState {

	long[] bucketIds;
	int[] bucketCounts;

	public SubWindowState(int numberOfBuckets) {

		bucketIds = new long[numberOfBuckets];
		bucketCounts = new int[numberOfBuckets];

		Arrays.fill(bucketIds, Long.MIN_VALUE);
	}

	public long[] getBucketIds() {
		return bucketIds;
	}

	public int[] getBucketCounts() {
		return bucketCounts;
	}
}
