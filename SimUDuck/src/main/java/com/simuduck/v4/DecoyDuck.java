package com.simuduck.v4;

import com.simuduck.v4.behavior.fly.FlyNoWay;
import com.simuduck.v4.behavior.fly.FlyWithWings;
import com.simuduck.v4.behavior.quack.MuteQuack;
import com.simuduck.v4.behavior.quack.Quack;

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
