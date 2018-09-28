package com.example.solamly.solamly.module.dagger.component;

import com.example.solamly.solamly.module.dagger.DaggerMainActivity_;

import dagger.Component;

/**
 * @Author SOLAMLY
 * @Date 2018/9/12 15:08
 * @Description:
 */

@Component
public interface FirstTestComponent {
    void inject(DaggerMainActivity_ activity);
}
