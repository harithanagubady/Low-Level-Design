package org.starbuzz.v1.decorator;

import org.starbuzz.v1.beverage.Beverage;

public abstract class CondimentDecorator extends Beverage {

	Beverage beverage;
	public abstract String getDescription();

	public Size getSize() {
		return beverage.getSize();
	}
}
