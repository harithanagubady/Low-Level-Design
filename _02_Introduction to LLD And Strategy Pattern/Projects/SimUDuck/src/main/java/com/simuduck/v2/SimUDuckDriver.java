package com.simuduck.v2;

public class SimUDuckDriver {

    public static void main(String[] args) {

        System.out.println("\nMallard Duck: ");
        Duck duck = new MallardDuck();
        duck.fly();
        duck.display();
        duck.swim();
        duck.quack();

        System.out.println("\nRedHead Duck: ");
        duck = new RedHeadDuck();
        duck.fly();
        duck.display();
        duck.swim();
        duck.quack();

        System.out.println("\nRubber Duck should not fly: ");
        duck = new RubberDuck();
        duck.fly();
        duck.display();
        duck.swim();
        duck.quack();

        System.out.println("\nDecoy Duck should not quack or fly: ");
        duck = new DecoyDuck();
        duck.fly();
        duck.display();
        duck.swim();
        duck.quack();

        System.out.println("Maintenance Overhead as fly behavior is in parent class and it requires all subclasses " +
                "to inherit this functionality");
    }
}
