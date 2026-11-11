package org.starbuzz.v0.decorator;

import org.starbuzz.v0.beverage.Beverage;

public class Mocha extends CondimentDecorator {

	public Mocha (Beverage beverage) {
		this.beverage = beverage;
	}

	@Override
	public String getDescription() {
		return beverage.getDescription() + ", Mocha";
	}

	@Override
	public double cost() {
		return beverage.cost() + .20;
	}
}
