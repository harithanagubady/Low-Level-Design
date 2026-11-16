package org.pizzastore.v2;

import org.pizzastore.v2.pizzaentity.Pizza;
import org.pizzastore.v2.store.ChicagoPizzaStore;
import org.pizzastore.v2.store.NYPizzaStore;
import org.pizzastore.v2.store.PizzaStore;

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
