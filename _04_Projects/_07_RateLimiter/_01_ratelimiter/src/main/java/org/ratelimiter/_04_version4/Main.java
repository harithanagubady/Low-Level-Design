package org.ratelimiter._04_version4;

import java.time.Clock;

import org.ratelimiter._04_version4.policy.WindowPolicy;
import org.ratelimiter._04_version4.store.InMemoryStateStore;
import org.ratelimiter._04_version4.store.RateLimitStateStore;
import org.ratelimiter._04_version4.strategy.FixedWindowStrategy;
import org.ratelimiter._04_version4.strategy.RateLimitStrategy;

//Extending support for all 6 algorithms
public class Main {
	public static void main(String[] args) {

		WindowPolicy policy = new WindowPolicy(2, 60_000);
		RateLimitStrategy strategy = new FixedWindowStrategy();
		RateLimitStateStore store = new InMemoryStateStore();

		RateLimiter limiter = new RateLimiter(policy, strategy, store, Clock.systemUTC());

		for (int i = 1; i <= 7; i++) {
			String client = "client-" + (i % 3);
			System.out.println("Request " + i + ", " + client + ": " + limiter.allowRequest(client));
		}
	}
}