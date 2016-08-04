package com.kkk.dagger2demo;

import dagger.Component;

/**
 * Created by CTC on 2016/8/4.
 */
@Component(modules = MainActivityModule.class)
public interface MainActivityComponent {
    void inject(MainActivity activity);
}
