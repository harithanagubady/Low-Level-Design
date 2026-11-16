package org.ratelimiter._03_version3;

import java.time.Clock;

import org.ratelimiter._03_version3.store.InMemoryStateStore;
import org.ratelimiter._03_version3.store.RateLimitStateStore;
import org.ratelimiter._03_version3.strategy.FixedWindowStrategy;
import org.ratelimiter._03_version3.strategy.RateLimitStrategy;

//Concurrency and thread-safety
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