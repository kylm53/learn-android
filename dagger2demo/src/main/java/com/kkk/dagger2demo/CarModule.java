package com.kkk.dagger2demo;

import com.kkk.dagger2demo.bean.BridgestoneTire;
import com.kkk.dagger2demo.bean.Engine;
import com.kkk.dagger2demo.bean.HondaEngine;
import com.kkk.dagger2demo.bean.MichelinTire;
import com.kkk.dagger2demo.bean.Tire;
import com.kkk.dagger2demo.bean.ToyotaEngine;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;

/**
 * Created by kkk on 2016/8/4.
 */
@Module
public class CarModule {
    @Named("honda")
    @Provides
    public Engine getHondaEngine() {
        return new HondaEngine();
    }

    @Named("toyota")
    @Provides
    public Engine getToyotaEngine() {
        return new ToyotaEngine();
    }

    @Named("michelin")
    @Provides
    public List<Tire> getMichelinTires() {
        return getTires(MichelinTire.class);
    }

    @Named("bridgestone")
    @Provides
    public List<Tire> getBridgestoneTires() {
        return getTires(BridgestoneTire.class);
    }

    private List<Tire> getTires(Class<? extends Tire> clz) {
        ArrayList<Tire> tires = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            Tire tire = null;
            try {
                tire = clz.newInstance();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            tires.add(tire);
        }
        return tires;
    }
}
