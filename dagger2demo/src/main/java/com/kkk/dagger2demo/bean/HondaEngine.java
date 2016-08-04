package com.kkk.dagger2demo.bean;

import javax.inject.Inject;

/**
 * Created by CTC on 2016/8/4.
 */
public class HondaEngine implements Engine {

    @Inject
    public HondaEngine() {
        System.out.println("new Honda Engine...");
    }

    @Override
    public void start() {
        System.out.println("Honda engine start...");
    }
}
