package org.pizzastore.v3.store;

import org.pizzastore.v3.ingrediententity.cheese.Cheese;
import org.pizzastore.v3.ingrediententity.clams.Clams;
import org.pizzastore.v3.ingrediententity.dough.Dough;
import org.pizzastore.v3.ingrediententity.clams.FreshClams;
import org.pizzastore.v3.ingrediententity.veggies.Garlic;
import org.pizzastore.v3.ingrediententity.sauce.MarinaraSauce;
import org.pizzastore.v3.ingrediententity.veggies.Mushroom;
import org.pizzastore.v3.ingrediententity.veggies.Onion;
import org.pizzastore.v3.ingrediententity.pepperoni.Pepperoni;
import org.pizzastore.v3.ingrediententity.veggies.RedPepper;
import org.pizzastore.v3.ingrediententity.cheese.ReggianoCheese;
import org.pizzastore.v3.ingrediententity.sauce.Sauce;
import org.pizzastore.v3.ingrediententity.pepperoni.SlicedPepperoni;
import org.pizzastore.v3.ingrediententity.dough.ThinCrustDough;
import org.pizzastore.v3.ingrediententity.veggies.Veggies;

public class NYPizzaIngredientFactory implements PizzaIngredientFactory {
	@Override
	public Dough createDough() {
		return new ThinCrustDough();
	}

	@Override
	public Sauce createSauce() {
		return new MarinaraSauce();
	}

	@Override
	public Cheese createCheese() {
		return new ReggianoCheese();
	}

	@Override
	public Veggies[] createVeggies() {
		Veggies veggies[] = {new Garlic(), new Onion(), new Mushroom(),new RedPepper()};
		return veggies;
	}

	@Override
	public Pepperoni createPepperoni() {
		return new SlicedPepperoni();
	}

	@Override
	public Clams createClam() {
		return new FreshClams();
	}
}
