package com.simuduck.v5;

import com.simuduck.v5.behavior.fly.FlyNoWay;
import com.simuduck.v5.behavior.quack.MuteQuack;

public class DecoyDuck extends Duck {
    public DecoyDuck () {

        quackBehavior = new MuteQuack();
        flyBehavior = new FlyNoWay();
    }

    @Override
    public void display() {
        System.out.println("Decoy duck brown color");
    }

}
