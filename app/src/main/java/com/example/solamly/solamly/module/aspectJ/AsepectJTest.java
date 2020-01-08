package com.example.solamly.solamly.module.aspectJ;

import android.util.Log;

import com.orhanobut.logger.Logger;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

/**
 * @Author: SOLAMLY
 * @Date: 2019/12/30 0030 10:25
 * @Description:
 */
@Aspect
public class AsepectJTest {

    private static final String TAG = "AsepectJ";

    @Pointcut("execution(@com.example.solamly.solamly.module.aspectJ.DeBugLog * *(..))")
    public void deBugTest(){}


    @Before("deBugTest()")
    public void logBefore(JoinPoint point){
        Logger.i("logBefore");
    }

    @AfterThrowing(value = "deBugTest()",throwing = "ex")
    public void logAfterThrowing(Throwable ex){
        Logger.e("logAfterThrowing : "+ex.getMessage());
    }
}
