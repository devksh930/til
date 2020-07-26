package me.devkh930.springaspect.demo;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class PerfAspect {
    @Around("@annotation(PerfLogging)") //poitcut 정의
    public Object logPerf(ProceedingJoinPoint pjp) throws Throwable {
        long begin = System.currentTimeMillis();
        Object retVal = pjp.proceed();
        System.out.println(System.currentTimeMillis() - begin);
        return retVal;
    }
    @Before("bean(simpleEventService)")
    public void hello (){
        System.out.println("hello");
    }
}
