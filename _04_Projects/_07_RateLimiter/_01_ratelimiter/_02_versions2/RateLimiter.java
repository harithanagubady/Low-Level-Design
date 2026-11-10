package _02_versions2;

public class RateLimiter {

	//Rule
	private final long windowDurationMillis;
	private final int maxRequests;

	//State
	private long windowStartTimeMillis;
	private long requestCount;

	public RateLimiter(int maxRequests, int windowDurationMillis) {
		this.maxRequests = maxRequests;
		this.windowDurationMillis = windowDurationMillis;
		this.windowStartTimeMillis = System.currentTimeMillis();
		this.requestCount = 0;
	}

	public boolean allowRequest() {
		long currentTime = System.currentTimeMillis();

		//Has the current window expired
		if (currentTime - windowStartTimeMillis >= windowDurationMillis) {
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
		System.out.println(rateLimiter.allowRequest());
		Thread.sleep(2000);
		System.out.println(rateLimiter.allowRequest());
		Thread.sleep(1000);
		System.out.println(rateLimiter.allowRequest());
		Thread.sleep(1000);
		System.out.println(rateLimiter.allowRequest());
		Thread.sleep(1000);
		System.out.println(rateLimiter.allowRequest());
		//Thread.sleep(5000);
		System.out.println(rateLimiter.allowRequest());
	}
}
