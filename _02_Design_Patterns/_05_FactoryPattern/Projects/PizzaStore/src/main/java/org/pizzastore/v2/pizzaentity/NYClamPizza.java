package org.pizzastore.v2.pizzaentity;

public class NYClamPizza extends Pizza {

	public NYClamPizza() {
		name = "NY Style Clam Pizza";
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
