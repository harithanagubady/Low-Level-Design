package com.simuduck.v5;

import com.simuduck.v5.behavior.fly.FlyRocketPowered;

public class SimUDuckDriver {

    public static void main(String[] args) {

        System.out.println("\nMallard Duck: ");
        Duck duck = new MallardDuck();
        duck.performFly();
        duck.display();
        duck.swim();
        duck.performQuack();

        System.out.println("\nRedHead Duck: ");
        duck = new RedHeadDuck();
        duck.performFly();
        duck.display();
        duck.swim();
        duck.performQuack();

        System.out.println("\nRubber Duck should not fly: ");
        duck = new RubberDuck();
        duck.performFly();
        duck.display();
        duck.swim();
        duck.performQuack();

        System.out.println("\nDecoy Duck should not quack or fly: ");
        duck = new DecoyDuck();
        duck.performFly();
        duck.display();
        duck.swim();
        duck.performQuack();

        System.out.println("\nModel Duck: ");
        duck = new ModelDuck();
        duck.display();
        duck.swim();
        duck.performQuack();
        duck.performFly();
        duck.setFlyBehavior(new FlyRocketPowered());
        duck.performFly();

        System.out.println("""
                \n
                * We have introduced set methods in Duck class to dynamically change fly and quack behaviors at runtime\s
                * This is the Strategy Pattern implementation""");
    }
}
