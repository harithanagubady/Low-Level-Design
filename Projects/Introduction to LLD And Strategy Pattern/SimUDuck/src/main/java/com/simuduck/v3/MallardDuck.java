package com.simuduck.v3;

public class MallardDuck extends Duck implements Flyable, Quackable {
    @Override
    public void display() {
        System.out.println("Mallard Duck White color");
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
