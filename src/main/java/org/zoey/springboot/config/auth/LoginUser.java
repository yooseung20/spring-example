package org.zoey.springboot.config.auth;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


// 이 어노테이션이 생성될 수 있는 위치를 지정한다.
// Parameter로 지정했으니 메소드의 파라미터로 선언된 객체에서만 사용할 수 있다.
// 이 외에도 클래스 선언문에 쓸 수 있는 TYPE 등이 있다.
@Target(ElementType.PARAMETER)
// 애노테이션의 라이프 사이클 즉, 애노테이션이 언제까지 살아 남아 있을지를 정하는 것
@Retention(RetentionPolicy.RUNTIME)

// 이파일을 어노테이션 클래스로 지정한다.
// LoginUser라는 이름을 가진 어노테이션이 생성되었다고 보면 된다.
public @interface LoginUser {
}
