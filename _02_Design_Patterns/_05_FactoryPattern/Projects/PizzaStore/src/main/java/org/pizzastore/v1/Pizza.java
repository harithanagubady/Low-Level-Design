package org.pizzastore.v1;

import java.util.ArrayList;
import java.util.List;

public class Pizza {

	String name;
	private String dough;
	private String sauce;
	private List<String> toppings = new ArrayList<>();

	public void prepare() {
		System.out.println("Preparing " + name);
		System.out.println("Tossing dough...");
		System.out.println("Adding sauce...");
		System.out.println("Adding toppings...");

		for(String topping: toppings) {
			System.out.println(" " + topping);
		}
	}

	public void bake() {
		System.out.println("Bake for 25 minutes at 350");
	}

	public void cut() {
		System.out.println("cutting the Pizza into diagonal slices");
	}

	public void box() {
		System.out.println("Place pizza in official PizzaStore box...");
	}

	public String getName() {
		return name;
	}
}
