package org.zoey.springboot.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.zoey.springboot.web.dto.HelloResponseDto;

// jSON을 반환하는 Controller로 만들어준다 .
@RestController
public class HelloController {

    // HTTP Method인 GET의 요청을 받을 수 있는 API를 만들어준다.
    @GetMapping("/hello")
    public String hello(){
        return "hello";

    }

    @GetMapping("/hello/dto")

    // @RequestParam
    // 외부에서 API로 넘긴 파라미터를 가져오는 어노테이션이다.
    // 여기서는 외부에서 name (@RequestParam("name"))이란 이름으로 넘긴 파라미터를 메소드 파라미터 name(String name)에 저장한다
    public HelloResponseDto helloDto(@RequestParam("name") String name,
                                     @RequestParam("amount") int amount) {
        return new HelloResponseDto(name, amount);
    }



}