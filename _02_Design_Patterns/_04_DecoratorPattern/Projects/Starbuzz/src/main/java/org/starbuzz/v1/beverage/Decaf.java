package org.starbuzz.v1.beverage;

public class Decaf extends Beverage {

	public Decaf() {
		description = "Decaf";
	}

	@Override
	public double cost() {
		return 0.79;
	}
}
