package com.kkk.dagger2demo;

import com.kkk.dagger2demo.bean.Car;

import dagger.Component;

/**
 * Created by CTC on 2016/8/4.
 */
@Component(modules = CarModule.class)
public interface CarComponent {
    void inject(Car car);
}
