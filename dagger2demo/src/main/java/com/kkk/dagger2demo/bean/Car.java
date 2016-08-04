package com.kkk.dagger2demo.bean;

import com.kkk.dagger2demo.DaggerCarComponent;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

/**
 * Created by CTC on 2016/8/3.
 */
public class Car {

    @Named("honda")
//    @Named("toyota")
    @Inject
    Engine mEngine;

    @Named("bridgestone")
//    @Named("michelin")
    @Inject
    List<Tire> mTires;

    @Inject
    public Car() {
        DaggerCarComponent.builder().build().inject(this);
    }

    public void run() {
        mEngine.start();
        System.out.println("I'm a car!!! running...");
    }
}
