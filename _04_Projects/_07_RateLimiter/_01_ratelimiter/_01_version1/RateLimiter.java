package _01_version1;

import java.time.LocalDateTime;

public class RateLimiter {

	//Rule
	private final long windowDurationMillis;
	private final int maxRequests;

	//State
	private long windowStartTimeMillis;
	private long requestCount;

	public RateLimiter (int maxRequests, int windowDurationMillis) {
		this.maxRequests = maxRequests;
		this.windowDurationMillis = windowDurationMillis;
		this.windowStartTimeMillis = System.currentTimeMillis();
		System.out.println("window start: " + windowStartTimeMillis);
		this.requestCount = 0;
	}

	public boolean allowRequest() {
		long currentTime = System.currentTimeMillis();
		System.out.println("current time: " + currentTime);

		//Has the current window expired
		if (currentTime - windowStartTimeMillis >= windowDurationMillis) {
			System.out.println("Window Reset --->");
			windowStartTimeMillis = currentTime;
			requestCount = 0;
		}

		//Have we exhausted the limit?
		if (requestCount >= maxRequests) {
			return false;
		}

		//Accept the request
		requestCount++;
		return true;
	}

	public static void main(String[] args) throws InterruptedException {
		RateLimiter rateLimiter = new RateLimiter(5, 5000);
		Thread.sleep(4500);
		System.out.println(rateLimiter.allowRequest());
		System.out.println(rateLimiter.allowRequest());
		System.out.println(rateLimiter.allowRequest());
		System.out.println(rateLimiter.allowRequest());
		System.out.println(rateLimiter.allowRequest());
		Thread.sleep(500);
		System.out.println(rateLimiter.allowRequest());
		System.out.println(rateLimiter.allowRequest());
		System.out.println(rateLimiter.allowRequest());
		System.out.println(rateLimiter.allowRequest());
		System.out.println(rateLimiter.allowRequest());
	}
}
