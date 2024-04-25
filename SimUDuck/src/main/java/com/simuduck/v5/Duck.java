package com.simuduck.v5;

import com.simuduck.v5.behavior.fly.FlyBehavior;
import com.simuduck.v5.behavior.quack.QuackBehavior;

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

    public void setFlyBehavior (FlyBehavior flyBehavior) {
        this.flyBehavior = flyBehavior;
    }

    public void setQuackBehavior (QuackBehavior quackBehavior) {
        this.quackBehavior = quackBehavior;
    }
}
