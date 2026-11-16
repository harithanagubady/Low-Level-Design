package org.pizzastore.v2.pizzaentity;

public class NYCheesePizza extends Pizza {

	public NYCheesePizza() {
		name = "NY Style Cheese Pizza";
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
