package com.simuduck.v5;

import com.simuduck.v5.behavior.fly.FlyWithWings;
import com.simuduck.v5.behavior.quack.Quack;

public class ModelDuck extends Duck {

    public ModelDuck () {
        flyBehavior = new FlyWithWings();
        quackBehavior = new Quack();
    }
    @Override
    public void display() {
        System.out.println("Model Duck...");
    }
}
