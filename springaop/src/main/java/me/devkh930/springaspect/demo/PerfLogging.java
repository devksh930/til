package me.devkh930.springaspect.demo;

import java.lang.annotation.*;

/*
 * 해당 에노테이션을 사용하면 성능측정을함
 */
//@Retention(RetentionPolicy.CLASS)
@Target(ElementType.METHOD)
@Documented
public @interface PerfLogging {
}
