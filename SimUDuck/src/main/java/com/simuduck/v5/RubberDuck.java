package com.simuduck.v5;

import com.simuduck.v5.behavior.fly.FlyNoWay;
import com.simuduck.v5.behavior.quack.Squeak;

public class RubberDuck extends Duck {

    public RubberDuck () {
        quackBehavior = new Squeak();
        flyBehavior = new FlyNoWay();
    }

    @Override
    public void display() {
        System.out.println("Rubber duck yellow color");
    }

}
