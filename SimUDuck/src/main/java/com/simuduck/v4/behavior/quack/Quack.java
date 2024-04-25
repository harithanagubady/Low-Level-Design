package com.simuduck.v4.behavior.quack;

public class Quack implements QuackBehavior {
    @Override
    public void quack() {
        System.out.println("Quack quack...");
    }
}
