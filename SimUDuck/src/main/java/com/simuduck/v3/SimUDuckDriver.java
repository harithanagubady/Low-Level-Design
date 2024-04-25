package com.simuduck.v3;

public class SimUDuckDriver {

    public static void main(String[] args) {

        System.out.println("\nMallard Duck: ");
        MallardDuck mallardDuck = new MallardDuck();
        mallardDuck.fly();
        mallardDuck.display();
        mallardDuck.swim();
        mallardDuck.quack();

        System.out.println("\nRedHead Duck: ");
        RedHeadDuck redHeadDuck = new RedHeadDuck();
        redHeadDuck.fly();
        redHeadDuck.display();
        redHeadDuck.swim();
        redHeadDuck.quack();

        System.out.println("\nRubber Duck should not fly: ");
        RubberDuck rubberDuck = new RubberDuck();
        rubberDuck.display();
        rubberDuck.swim();
        rubberDuck.quack();

        System.out.println("\nDecoy Duck should not quack or fly: ");
        DecoyDuck decoyDuck = new DecoyDuck();
        decoyDuck.display();
        decoyDuck.swim();

        System.out.println("We have separated out what varies from what remains same by implementing interfaces " +
                "Flyable and Quackable. We let each class implement Flyable and Quackable interfaces to implement " +
                "its own fly and quack methods but reusability is an issue. Because if there is a small change " +
                "in common fly behavior, it needs to be updated in all classes. It can also be maintenance overhead");
    }
}
