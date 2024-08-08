package com.simuduck.v4.behavior.fly;

import com.simuduck.v4.behavior.fly.FlyBehavior;

public class FlyWithWings implements FlyBehavior {
    @Override
    public void fly() {
        System.out.println("Flying...");
    }
}
