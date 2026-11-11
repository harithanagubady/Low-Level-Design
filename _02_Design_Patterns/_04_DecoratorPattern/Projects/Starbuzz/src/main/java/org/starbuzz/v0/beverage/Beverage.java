package org.starbuzz.v0.beverage;

public abstract class Beverage {

	String description = "Unknown Beverage";

	public String getDescription() {
		return description;
	}

	public abstract double cost();
}
