package org.pizzastore.v3.store;

import org.pizzastore.v3.ingrediententity.cheese.Cheese;
import org.pizzastore.v3.ingrediententity.clams.Clams;
import org.pizzastore.v3.ingrediententity.dough.Dough;
import org.pizzastore.v3.ingrediententity.pepperoni.Pepperoni;
import org.pizzastore.v3.ingrediententity.sauce.Sauce;
import org.pizzastore.v3.ingrediententity.veggies.Veggies;

public interface  PizzaIngredientFactory {

	public Dough createDough();
	public Sauce createSauce();
	public Cheese createCheese();
	public Veggies[] createVeggies();
	public Pepperoni createPepperoni();
	public Clams createClam();
}
