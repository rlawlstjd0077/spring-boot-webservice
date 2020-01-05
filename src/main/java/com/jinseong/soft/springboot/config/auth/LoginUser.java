package com.jinseong.soft.springboot.config.auth;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

//메소드의 파라미터로 선언된 객체에서만 사용될 수 있음을 의미
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
//어노테이션 클래스로 지정
public @interface LoginUser {
}
