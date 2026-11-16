package org.pizzastore.v2.store;

import org.pizzastore.v2.pizzaentity.NYCheesePizza;
import org.pizzastore.v2.pizzaentity.NYClamPizza;
import org.pizzastore.v2.pizzaentity.NYPepperoniPizza;
import org.pizzastore.v2.pizzaentity.NYVeggiePizza;
import org.pizzastore.v2.pizzaentity.Pizza;

public class NYPizzaStore extends PizzaStore {

	@Override
	protected Pizza createPizza(String type) {

		Pizza pizza = null;

		if (type.equals("cheese")) {
			pizza = new NYCheesePizza();
		} else if(type.equals("pepperoni")) {
			pizza = new NYPepperoniPizza();
		} else if(type.equals("clam")) {
			pizza = new NYClamPizza();
		} else if(type.equals("veggie")) {
			pizza = new NYVeggiePizza();
		}
		return pizza;
	}
}
