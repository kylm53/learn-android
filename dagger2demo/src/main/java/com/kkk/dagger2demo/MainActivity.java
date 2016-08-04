package com.kkk.dagger2demo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.kkk.dagger2demo.bean.Car;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity {
    @Inject
    Car car;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DaggerMainActivityComponent.builder().build().inject(this);
        car.run();
    }
}
