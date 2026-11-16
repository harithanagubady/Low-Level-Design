package org.pizzastore.v2.pizzaentity;

public class ChicagoCheesePizza extends Pizza {

	public ChicagoCheesePizza() {
		name = "Chicago Style Cheese Pizza";
	}

	@Override
	public void cut() {
		System.out.println("cutting the Pizza into square slices");
	}

	public void prepare() {
		System.out.println("Preparing " + name);
		System.out.println("Tossing dough...");
		System.out.println("Adding sauce...");
		System.out.println("Adding toppings...");

		for(String topping: toppings) {
			System.out.println(" " + topping);
		}
	}
}
