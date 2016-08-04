package com.kkk.dagger2demo.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Qualifier;

/**
 * Created by kkk on 2016/8/4.
 * int named annotation
 */
@Qualifier
@Documented
@Retention(RetentionPolicy.RUNTIME)
public @interface IntNamed {
    int value();
}
