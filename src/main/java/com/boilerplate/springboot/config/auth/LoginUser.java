package com.boilerplate.springboot.config.auth;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

//메소드의 파라미터로 선언된 객체에서만 어노테이션이 생성될 수 있다.
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
// 이 파일을 어노테이션으로 지정한다. (@LoginUser)
public @interface LoginUser {
}
