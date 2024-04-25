package com.simuduck.v4;

import com.simuduck.v4.behavior.fly.FlyBehavior;
import com.simuduck.v4.behavior.quack.QuackBehavior;

public abstract class Duck {

    FlyBehavior flyBehavior;
    QuackBehavior quackBehavior;
    public void swim() {
        System.out.println("Swimming");
    }

    public abstract void display();

    public void performQuack() {
        quackBehavior.quack();
    }

    public void performFly() {
        flyBehavior.fly();
    }
}
