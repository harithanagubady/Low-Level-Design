package com.simuduck.v3;

public class RubberDuck extends Duck implements Quackable {

    @Override
    public void quack() {
        System.out.println("Squeak...");
    }

    @Override
    public void display() {
        System.out.println("Rubber duck yellow color");
    }

}
