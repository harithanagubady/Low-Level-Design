package com.simuduck.v1;

public abstract class Duck {

    public void quack() {
        System.out.println("Quack quack...");
    }

    public void swim() {
        System.out.println("Swimming");
    }

    public abstract void display();
}
