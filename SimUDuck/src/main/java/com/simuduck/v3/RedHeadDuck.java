package com.simuduck.v3;

public class RedHeadDuck extends Duck implements Flyable, Quackable {
    @Override
    public void display() {
        System.out.println("ReadHead Duck Red and White color");
    }

    @Override
    public void fly() {
        System.out.println("Flying...");
    }

    @Override
    public void quack() {
        System.out.println("Quack quack...");
    }
}
