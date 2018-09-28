package com.example.solamly.solamly.module.design_mode.factory;

import com.example.solamly.solamly.Base.BaseActivity;
import com.example.solamly.solamly.R;

/**
 * @Author SOLAMLY
 * @Date 2018/9/27 9:45
 * @Description:
 */

public class FactoryActivity extends BaseActivity {
    @Override
    protected int setLayout() {
        return R.layout.activity_factory;
    }

    @Override
    protected void initView() {
       FruitsFactorys factorys = new FruitsFactorys();
        Fruits apple = factorys.getFruits(FruitsFactorys.Apple);
        Fruits peach = factorys.getFruits(FruitsFactorys.Peach);
        apple.getPrice();
        peach.getPrice();
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void setListener() {

    }
}
