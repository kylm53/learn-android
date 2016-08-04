package com.kkk.dagger2demo;

import com.kkk.dagger2demo.bean.Car;

import dagger.Module;
import dagger.Provides;

/**
 * Created by CTC on 2016/8/4.
 */
@Module
public class MainActivityModule {
    @Provides
    public Car provideCar() {
        return new Car();
    }
}
