package com.simuduck.v4;

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

        System.out.println("""
                \n
                * We have removed dynamic behaviors from concrete classes\s
                * We delegated the dynamic behaviors to separate behavior classes FlyWithWings, Quack, Squeak,...\s
                * Existing as well as new duck classes can simply REUSE these behaviors\s
                * We can add new behaviors without changing any existing classes\s
                * We added reference variables of fly and quack interfaces, performQuack and performFly methods to
                 Duck parent class\s
                * We are not achieving full flexibility as we are injecting these behaviors at runtime instead
                 we are injecting these behaviors in constructors of concrete classes""");
    }
}
