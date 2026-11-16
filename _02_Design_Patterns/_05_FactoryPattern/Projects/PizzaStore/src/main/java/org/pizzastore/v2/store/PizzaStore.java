package org.pizzastore.v2.store;

import org.pizzastore.v2.pizzaentity.Pizza;

public abstract class PizzaStore {

	private Pizza pizza;

	public Pizza orderPizza (String type) {

		pizza = createPizza(type);

		pizza.prepare();
		pizza.bake();
		pizza.cut();
		pizza.box();

		return pizza;
	}

	protected abstract Pizza createPizza(String type);
}
