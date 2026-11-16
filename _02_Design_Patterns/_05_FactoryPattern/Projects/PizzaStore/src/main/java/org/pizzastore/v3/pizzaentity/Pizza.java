package org.pizzastore.v3.pizzaentity;

import org.pizzastore.v3.ingrediententity.cheese.Cheese;
import org.pizzastore.v3.ingrediententity.clams.Clams;
import org.pizzastore.v3.ingrediententity.dough.Dough;
import org.pizzastore.v3.ingrediententity.pepperoni.Pepperoni;
import org.pizzastore.v3.ingrediententity.sauce.Sauce;
import org.pizzastore.v3.ingrediententity.veggies.Veggies;

public abstract class Pizza {

	String name;
	Dough dough;
	Sauce sauce;
	Veggies veggies[];
	Cheese cheese;
	Pepperoni pepperoni;
	Clams clam;

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

	public void setName(String name) {
		this.name = name;
	}
}
