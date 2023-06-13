package com.comibird.config.auth;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.PARAMETER) // 메서드의 파라미터에만 적용되도록
@Retention(RetentionPolicy.RUNTIME)
public @interface LoginUser {
}
