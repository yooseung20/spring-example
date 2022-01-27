package org.zoey.springboot.web;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;


import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@RunWith(SpringRunner.class) //junit4에서 사용, junit5부터 생략가능

// 스프링 테스트 어노테이션 중, Web(Spring MVC)에 집중할 수 있는 어노테이션
// @Controller, @ControllerAdvice등을 사용할 수 있다.
@WebMvcTest(controllers = HelloController.class)

public class HelloControllerTest {

    //스프링이 관리하는 Bean을 주입 받는다.
    @Autowired

    // 웹 API를 테스트할 때 사용한다
    // 스프링 MVC 테스트의 시작점
    // 이 클래스를 통해 HTTP GET, POST 등에 대한 API 테스트를 할 수 있다.
    private MockMvc mvc;

    @Test
    public void hello가_리턴된다() throws Exception {
        String hello = "hello";

        // MockMvc를 통해 /hello 주소로 HTTP GET요청을 한다.
        // 체이닝이 지원되어 아래와 같이 여러 검증 기능을 이어서 선언 할 수 있다.
        mvc.perform(get("/hello"))
                // mvc.perform의 결과를 검증한다.
                // HTTP Header의 Status를 검증한다.
                // 우리가 흔히 알고 있는 200, 404, 500 등의 상태를 검증한다
                // 여기선 200인지 아닌지를 검증한다.(정상 구동되는지 확인)
                .andExpect(status().isOk())

                // mvc.perform의 결과를 검증한다.
                // 응답 본문의 내용을 검증한다.
                // Controller에서 "hello"를 return 하기 때문에 이 값이 맞는지 검증한다.
                .andExpect(content().string(hello));
    }

    @Test
    public void helloDto가_리턴된다() throws Exception {
        String name = "hello";
        int amount = 1000;

        mvc.perform(get("/hello/dto")

                        // API 테스트할 때 사용될 요청 파라미터를 설정합니다.
                        // string만 허용한다. ( int(valueOf)-> string)
                        .param("name", name)
                        .param("amount", String.valueOf(amount)))
                .andExpect(status().isOk())
                // JsonPath
                // $을 기준으로 필드명을 명시합니다.
                .andExpect(jsonPath("$.name", is(name)))
                .andExpect(jsonPath("$.amount", is(amount)));
    }
}
