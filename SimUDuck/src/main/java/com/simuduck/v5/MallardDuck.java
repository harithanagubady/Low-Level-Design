package com.simuduck.v5;

import com.simuduck.v5.behavior.fly.FlyWithWings;
import com.simuduck.v5.behavior.quack.Quack;

public class MallardDuck extends Duck {

    public MallardDuck () {
        quackBehavior = new Quack();
        flyBehavior = new FlyWithWings();
    }

    @Override
    public void display() {
        System.out.println("Mallard Duck White color");
    }
}
