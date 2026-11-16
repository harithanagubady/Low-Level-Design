package org.pizzastore.v3.store;

import org.pizzastore.v3.pizzaentity.CheesePizza;
import org.pizzastore.v3.pizzaentity.ClamPizza;
import org.pizzastore.v3.pizzaentity.PepperoniPizza;
import org.pizzastore.v3.pizzaentity.VeggiePizza;
import org.pizzastore.v3.pizzaentity.Pizza;

public class NYPizzaStore extends PizzaStore {

	@Override
	protected Pizza createPizza(String type) {

		Pizza pizza = null;

		PizzaIngredientFactory ingredientFactory = new NYPizzaIngredientFactory();
		if (type.equals("cheese")) {
			pizza = new CheesePizza(ingredientFactory);
			pizza.setName("New York Style Cheese Pizza");
		} else if(type.equals("pepperoni")) {
			pizza = new PepperoniPizza(ingredientFactory);
			pizza.setName("New York Style Pepperoni Pizza");
		} else if(type.equals("clam")) {
			pizza = new ClamPizza(ingredientFactory);
			pizza.setName("New York Style Clam Pizza");
		} else if(type.equals("veggie")) {
			pizza = new VeggiePizza(ingredientFactory);
			pizza.setName("New York Style Veggie Pizza");
		}
		return pizza;
	}
}
