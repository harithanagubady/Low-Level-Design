package org.pizzastore.v2.store;

import org.pizzastore.v2.pizzaentity.ChicagoCheesePizza;
import org.pizzastore.v2.pizzaentity.ChicagoClamPizza;
import org.pizzastore.v2.pizzaentity.ChicagoPepperoniPizza;
import org.pizzastore.v2.pizzaentity.ChicagoVeggiePizza;
import org.pizzastore.v2.pizzaentity.NYCheesePizza;
import org.pizzastore.v2.pizzaentity.NYClamPizza;
import org.pizzastore.v2.pizzaentity.NYPepperoniPizza;
import org.pizzastore.v2.pizzaentity.NYVeggiePizza;
import org.pizzastore.v2.pizzaentity.Pizza;

public class DependentPizzaStore {

	public Pizza createPizza(String style, String type) {

		Pizza pizza = null;

		if(style.equals("NY")){
			if (type.equals("cheese")) {
				pizza = new NYCheesePizza();
			} else if(type.equals("pepperoni")) {
				pizza = new NYPepperoniPizza();
			} else if(type.equals("clam")) {
				pizza = new NYClamPizza();
			} else if(type.equals("veggie")) {
				pizza = new NYVeggiePizza();
			}
		} else if(style.equals("Chicago")){
			if (type.equals("cheese")) {
				pizza = new ChicagoCheesePizza();
			} else if (type.equals("pepperoni")) {
				pizza = new ChicagoPepperoniPizza();
			} else if (type.equals("clam")) {
				pizza = new ChicagoClamPizza();
			} else if (type.equals("veggie")) {
				pizza = new ChicagoVeggiePizza();
			}
		} else {
			System.out.println("Error: invalid type of pizza");
			return null;
		}

		pizza.prepare();
		pizza.bake();
		pizza.cut();
		pizza.box();

		return pizza;
	}
}
