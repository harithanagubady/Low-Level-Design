package org.pizzastore.v2.store;

import org.pizzastore.v2.pizzaentity.ChicagoCheesePizza;
import org.pizzastore.v2.pizzaentity.ChicagoClamPizza;
import org.pizzastore.v2.pizzaentity.ChicagoPepperoniPizza;
import org.pizzastore.v2.pizzaentity.ChicagoVeggiePizza;
import org.pizzastore.v2.pizzaentity.Pizza;

public class ChicagoPizzaStore extends PizzaStore {

	@Override
	protected Pizza createPizza(String type) {

		Pizza pizza = null;

		if (type.equals("cheese")) {
			pizza = new ChicagoCheesePizza();
		} else if(type.equals("pepperoni")) {
			pizza = new ChicagoPepperoniPizza();
		} else if(type.equals("clam")) {
			pizza = new ChicagoClamPizza();
		} else if(type.equals("veggie")) {
			pizza = new ChicagoVeggiePizza();
		}
		return pizza;
	}
}
