package org.starbuzz.v1.decorator;

import org.starbuzz.v1.beverage.Beverage;

public class Soy extends CondimentDecorator {

	public Soy(Beverage beverage) {
		this.beverage = beverage;
	}

	@Override
	public String getDescription() {
		return beverage.getDescription() + ", Soy";
	}

	@Override
	public double cost() {

		double cost = beverage.cost();

		if (beverage.getSize() == Size.TALL) {
			cost += 0.10;
		} else if (beverage.getSize() == Size.GRANDE) {
			cost += 0.15;
		} else {
			cost += 0.20;
		}
		return cost;
	}
}
