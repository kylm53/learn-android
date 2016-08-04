package com.kkk.dagger2demo.bean;

import javax.inject.Inject;

/**
 * Created by CTC on 2016/8/4.
 */
public class ToyotaEngine implements Engine {

    @Inject
    public ToyotaEngine() {
        System.out.println("new Toyota engine...");
    }

    @Override
    public void start() {
        System.out.println("Toyota engine start...");
    }
}
