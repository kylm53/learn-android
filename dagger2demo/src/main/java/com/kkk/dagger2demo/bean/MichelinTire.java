package com.kkk.dagger2demo.bean;

import javax.inject.Inject;

/**
 * Created by CTC on 2016/8/4.
 */
public class MichelinTire implements Tire {
    @Inject
    public MichelinTire() {
        System.out.println("new michelin tire...");
    }
}
