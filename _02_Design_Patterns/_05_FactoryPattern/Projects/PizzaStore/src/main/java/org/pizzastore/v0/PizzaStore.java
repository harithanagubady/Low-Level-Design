package org.pizzastore.v0;

public class PizzaStore {

	private Pizza pizza;

	public Pizza orderPizza (String type) {

		if (type.equals("cheese")) {
			pizza = new CheesePizza();
		} else if(type.equals("pepperoni")) {
			pizza = new PepperoniPizza();
		} else if(type.equals("clam")) {
			pizza = new ClamPizza();
		} else if(type.equals("veggie")) {
			pizza = new VeggiePizza();
		}

		pizza.prepare();
		pizza.bake();
		pizza.cut();
		pizza.box();

		return pizza;
	}
}
