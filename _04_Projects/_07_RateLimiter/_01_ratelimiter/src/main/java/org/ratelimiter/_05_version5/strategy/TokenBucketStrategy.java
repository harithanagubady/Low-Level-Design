package org.ratelimiter._05_version5.strategy;

import org.ratelimiter._05_version5.policy.RateLimitPolicy;
import org.ratelimiter._05_version5.policy.TokenBucketPolicy;
import org.ratelimiter._05_version5.state.RateLimitState;
import org.ratelimiter._05_version5.state.TokenBucketState;

public class TokenBucketStrategy implements RateLimitStrategy {

	@Override
	public RateLimitState createState(RateLimitPolicy policy, long now) {
		TokenBucketPolicy tokenBucketPolicy = (TokenBucketPolicy) policy;
		return new TokenBucketState(tokenBucketPolicy.getCapacity(), now);
	}

	@Override
	public boolean allowRequest(RateLimitState state, RateLimitPolicy policy, long now) {

		TokenBucketState tokenBucketState = (TokenBucketState) state;
		TokenBucketPolicy tokenBucketPolicy = (TokenBucketPolicy) policy;

		long elapsed = now - tokenBucketState.getLastRefillTimeMillis();
		long refillPeriods = elapsed / tokenBucketPolicy.getRefillPeriodMillis();

		if (refillPeriods > 0) {

			double tokensToAdd = refillPeriods * (double) tokenBucketPolicy.getRefillTokens();
			double updatedTokens = Math.min(tokenBucketPolicy.getCapacity(),
					tokenBucketState.getAvailableTokens() + tokensToAdd);

			tokenBucketState.setAvailableTokens(updatedTokens);

			tokenBucketState.setLastRefillTimeMillis(tokenBucketState.getLastRefillTimeMillis()
					+ refillPeriods * tokenBucketPolicy.getRefillPeriodMillis());
		}

		if (tokenBucketState.getAvailableTokens() < 1) {
			return false;
		}

		tokenBucketState.setAvailableTokens(tokenBucketState.getAvailableTokens() - 1);

		return true;
	}
}
