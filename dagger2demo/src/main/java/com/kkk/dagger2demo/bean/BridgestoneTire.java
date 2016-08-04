package com.kkk.dagger2demo.bean;

import javax.inject.Inject;

/**
 * Created by CTC on 2016/8/4.
 */
public class BridgestoneTire implements Tire {
    @Inject
    public BridgestoneTire() {
        System.out.println("new bridgesttone tire...");
    }
}
