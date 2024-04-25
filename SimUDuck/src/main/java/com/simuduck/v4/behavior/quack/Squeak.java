package com.simuduck.v4.behavior.quack;

public class Squeak implements QuackBehavior {
    @Override
    public void quack() {
        System.out.println("Squeak...");
    }
}
