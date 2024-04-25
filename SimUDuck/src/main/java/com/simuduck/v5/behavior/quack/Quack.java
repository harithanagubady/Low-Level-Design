package com.simuduck.v5.behavior.quack;

public class Quack implements QuackBehavior {
    @Override
    public void quack() {
        System.out.println("Quack quack...");
    }
}
