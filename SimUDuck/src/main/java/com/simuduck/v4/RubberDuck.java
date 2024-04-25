package com.simuduck.v4;

import com.simuduck.v4.behavior.fly.FlyNoWay;
import com.simuduck.v4.behavior.fly.FlyWithWings;
import com.simuduck.v4.behavior.quack.Quack;
import com.simuduck.v4.behavior.quack.Squeak;

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
