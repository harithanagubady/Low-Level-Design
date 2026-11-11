package org.starbuzz.v1;

import org.starbuzz.v1.beverage.Beverage;
import org.starbuzz.v1.beverage.DarkRoast;
import org.starbuzz.v1.beverage.Espresso;
import org.starbuzz.v1.beverage.HouseBlend;
import org.starbuzz.v1.decorator.Mocha;
import org.starbuzz.v1.decorator.Soy;
import org.starbuzz.v1.decorator.Whip;

public class Main {


	public static void main(String[] args) {
		Beverage beverage = new Espresso();
		System.out.println(beverage.getDescription() + " $" + beverage.cost());

		Beverage beverage2 = new DarkRoast();
		beverage2 = new Mocha(beverage2); //single Mocha
		beverage2 = new Mocha(beverage2); //double Mocha
		beverage2 = new Whip(beverage2);
		System.out.println(beverage2.getDescription() + " $" + beverage2.cost());

		Beverage beverage3 = new HouseBlend();
		beverage3 = new Soy(beverage3);
		beverage3 = new Mocha(beverage3);
		beverage3 = new Whip(beverage3);
		System.out.println(beverage3.getDescription() + " $" + beverage3.cost());

	}
}
