package org.pizzastore.v3;

import org.pizzastore.v3.pizzaentity.Pizza;
import org.pizzastore.v3.store.ChicagoPizzaStore;
import org.pizzastore.v3.store.NYPizzaStore;
import org.pizzastore.v3.store.PizzaStore;

public class Main {

	public static void main(String[] args) {

		PizzaStore pizzaStore = new NYPizzaStore();
		Pizza pizza = pizzaStore.orderPizza("cheese");

		System.out.println("Ethan ordered a " + pizza.getName());
		System.out.println();

		PizzaStore pizzaStore1 = new ChicagoPizzaStore();
		Pizza pizza1 = pizzaStore1.orderPizza("cheese");

		System.out.println("Joel ordered a " + pizza1.getName());
	}
}
