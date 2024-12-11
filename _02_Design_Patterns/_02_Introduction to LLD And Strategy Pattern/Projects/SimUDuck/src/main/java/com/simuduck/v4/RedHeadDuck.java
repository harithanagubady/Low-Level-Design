package com.simuduck.v4;

import com.simuduck.v4.behavior.fly.FlyWithWings;
import com.simuduck.v4.behavior.quack.Quack;

public class RedHeadDuck extends Duck {

    public RedHeadDuck () {
        quackBehavior = new Quack();
        flyBehavior = new FlyWithWings();
    }

    @Override
    public void display() {
        System.out.println("ReadHead Duck Red and White color");
    }

}
