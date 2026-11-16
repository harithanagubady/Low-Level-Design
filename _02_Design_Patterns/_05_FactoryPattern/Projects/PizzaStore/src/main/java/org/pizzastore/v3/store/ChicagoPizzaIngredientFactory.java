package org.pizzastore.v3.store;

import org.pizzastore.v3.ingrediententity.cheese.Cheese;
import org.pizzastore.v3.ingrediententity.clams.Clams;
import org.pizzastore.v3.ingrediententity.dough.Dough;
import org.pizzastore.v3.ingrediententity.clams.FrozenClams;
import org.pizzastore.v3.ingrediententity.veggies.Garlic;
import org.pizzastore.v3.ingrediententity.cheese.MozarellaCheese;
import org.pizzastore.v3.ingrediententity.veggies.Mushroom;
import org.pizzastore.v3.ingrediententity.veggies.Onion;
import org.pizzastore.v3.ingrediententity.pepperoni.Pepperoni;
import org.pizzastore.v3.ingrediententity.sauce.PlumTomatoSauce;
import org.pizzastore.v3.ingrediententity.veggies.RedPepper;
import org.pizzastore.v3.ingrediententity.sauce.Sauce;
import org.pizzastore.v3.ingrediententity.pepperoni.SlicedPepperoni;
import org.pizzastore.v3.ingrediententity.dough.ThickCrustDough;
import org.pizzastore.v3.ingrediententity.veggies.Veggies;

public class ChicagoPizzaIngredientFactory implements PizzaIngredientFactory {
	@Override
	public Dough createDough() {
		return new ThickCrustDough();
	}

	@Override
	public Sauce createSauce() {
		return new PlumTomatoSauce();
	}

	@Override
	public Cheese createCheese() {
		return new MozarellaCheese();
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
		return new FrozenClams();
	}
}
