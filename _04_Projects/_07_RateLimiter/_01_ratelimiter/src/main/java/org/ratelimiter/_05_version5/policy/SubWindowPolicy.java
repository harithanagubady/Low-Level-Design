package org.ratelimiter._05_version5.policy;

public class SubWindowPolicy extends WindowPolicy {

	private final int numberOfBuckets;

	public SubWindowPolicy(int maxRequests, long windowDurationMillis, int numberOfBuckets) {
		super(maxRequests, windowDurationMillis);

		if (numberOfBuckets <= 0 || windowDurationMillis % numberOfBuckets != 0) {
			throw new IllegalArgumentException("Window duration must be divisible by bucket count");
		}

		this.numberOfBuckets = numberOfBuckets;
	}

	public int getNumberOfBuckets() {
		return numberOfBuckets;
	}

	public long getBucketDurationMillis() {
		return getWindowDurationMillis() / numberOfBuckets;
	}
}
