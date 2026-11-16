package org.pizzastore.v0;

public class Main {

	public static void main(String[] args) {

		PizzaStore pizzaStore = new PizzaStore();
		Pizza pizza = pizzaStore.orderPizza("cheese");

		System.out.println("Ethan ordered a " + pizza.getName());
	}
}
