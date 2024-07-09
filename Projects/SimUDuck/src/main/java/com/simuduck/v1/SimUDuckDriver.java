package com.simuduck.v1;

public class SimUDuckDriver {

    public static void main(String[] args) {
        Duck duck = new RubberDuck();
        duck.display();
        duck = new MallardDuck();
        duck.display();
        duck = new RedHeadDuck();
        duck.display();
    }
}
