package org.starbuzz.v0.decorator;

import org.starbuzz.v0.beverage.Beverage;

public abstract class CondimentDecorator extends Beverage {

	Beverage beverage;
	public abstract String getDescription();
}
