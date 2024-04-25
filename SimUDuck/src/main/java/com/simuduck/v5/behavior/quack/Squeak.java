package com.simuduck.v5.behavior.quack;

public class Squeak implements QuackBehavior {
    @Override
    public void quack() {
        System.out.println("Squeak...");
    }
}
