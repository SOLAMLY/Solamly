package com.example.solamly.solamly.module.design_mode.factory;

/**
 * @Author SOLAMLY
 * @Date 2018/9/27 9:38
 * @Description:
 */

public class FruitsFactorys {
    public static final String Apple = "apple";
    public static final String Peach = "peach";
    public Fruits getFruits(String type){
        if (type.equalsIgnoreCase(Apple)) {
            return new Apple();
        }else if (type.equalsIgnoreCase(Peach)){
            return new Peach();
        }else{
            return null;
        }
    }

}
