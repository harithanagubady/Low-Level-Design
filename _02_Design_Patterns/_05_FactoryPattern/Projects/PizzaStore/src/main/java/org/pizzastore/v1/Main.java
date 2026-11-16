package org.pizzastore.v1;

public class Main {

	public static void main(String[] args) {

		SimplePizzaFactory factory = new SimplePizzaFactory();

		PizzaStore pizzaStore = new PizzaStore(factory);
		Pizza pizza = pizzaStore.orderPizza("cheese");

		System.out.println("Ethan ordered a " + pizza.getName());
	}
}
