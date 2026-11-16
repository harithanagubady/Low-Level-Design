package org.pizzastore.v2.pizzaentity;

import java.util.ArrayList;
import java.util.List;

public abstract class Pizza {

	String name;
	String dough;
	String sauce;
	List<String> toppings = new ArrayList<>();

	public abstract void prepare();

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
