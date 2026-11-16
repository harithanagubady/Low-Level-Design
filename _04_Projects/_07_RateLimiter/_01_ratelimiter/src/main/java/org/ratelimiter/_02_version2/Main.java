package org.ratelimiter._02_version2;

import java.time.Clock;

import org.ratelimiter._02_version2.store.InMemoryStateStore;
import org.ratelimiter._02_version2.store.RateLimitStateStore;
import org.ratelimiter._02_version2.strategy.FixedWindowStrategy;
import org.ratelimiter._02_version2.strategy.RateLimitStrategy;

public class Main {
	public static void main(String[] args) {

		RateLimitPolicy policy = new RateLimitPolicy(2, 60_000);
		RateLimitStrategy strategy = new FixedWindowStrategy();
		RateLimitStateStore store = new InMemoryStateStore();

		RateLimiter limiter = new RateLimiter(policy, strategy, store, Clock.systemUTC());

		for (int i = 1; i <= 7; i++) {
			String client = "client-" + (i % 3);
			System.out.println("Request " + i + ", " + client + ": " + limiter.allowRequest(client));
		}
	}
}