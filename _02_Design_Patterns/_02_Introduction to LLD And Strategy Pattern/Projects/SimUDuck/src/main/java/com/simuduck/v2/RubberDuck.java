package com.simuduck.v2;

public class RubberDuck extends Duck {

    //quack is overridden as Rubber Duck has its own type of quacking
    @Override
    public void quack() {
        System.out.println("Squeak...");
    }

    @Override
    public void display() {
        System.out.println("Rubber duck yellow color");
    }

    @Override
    public void fly() {

    }
}
