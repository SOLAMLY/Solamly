package com.example.solamly.solamly.module.dagger.component;

import com.example.solamly.solamly.module.dagger.DaggerMainActivity;
import com.example.solamly.solamly.module.dagger.module.HttpModule;

import dagger.Component;

/**
 * @Author SOLAMLY
 * @Date 2018/9/12 16:04
 * @Description:
 */

@Component(modules = HttpModule.class)
public interface HttpComponse {
   void inject(DaggerMainActivity activity);
}
