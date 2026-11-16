package org.ratelimiter._05_version5.state;

import java.util.Arrays;

public class SubWindowState extends RateLimitState {

	private final long[] bucketIds;
	private final int[] bucketCounts;

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
