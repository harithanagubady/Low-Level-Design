package org.ratelimiter._01_version1;

import java.time.Clock;

import org.ratelimiter._01_version1.strategy.FixedWindowStrategy;
import org.ratelimiter._01_version1.strategy.RateLimitStrategy;

public class Main {
	public static void main(String[] args) {

		RateLimitPolicy policy = new RateLimitPolicy(5, 60_000);
		RateLimitStrategy strategy = new FixedWindowStrategy();

		RateLimiter limiter = new RateLimiter(policy, strategy, Clock.systemUTC());

		for (int i = 1; i <= 6; i++) {
			System.out.println("Request " + i + ": " + limiter.allowRequest());
		}
	}
}