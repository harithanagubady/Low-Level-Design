package org.pizzastore.v1;

public class PizzaStore {

	private Pizza pizza;
	SimplePizzaFactory factory;

	public PizzaStore (SimplePizzaFactory factory) {
		this.factory = factory;
	}

	public Pizza orderPizza (String type) {

		pizza = factory.createPizza(type);

		pizza.prepare();
		pizza.bake();
		pizza.cut();
		pizza.box();

		return pizza;
	}
}
