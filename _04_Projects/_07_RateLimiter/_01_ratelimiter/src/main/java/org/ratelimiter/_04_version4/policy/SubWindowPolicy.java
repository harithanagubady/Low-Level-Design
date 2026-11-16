package org.ratelimiter._04_version4.policy;

public class SubWindowPolicy extends WindowPolicy {

	private final int numberOfBuckets;

	public SubWindowPolicy(int maxRequests, long windowDurationMillis) {
		super(maxRequests, windowDurationMillis);
		numberOfBuckets = (int) windowDurationMillis / maxRequests;
	}
}
