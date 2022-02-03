package org.zoey.springboot.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

// jSON을 반환하는 Controller로 만들어준다 .
@Controller
public class IndexController {
    @GetMapping("/")
    public String index(){
        return "index";
    }

    // posts/save를 호출하면 posts-save.mustache
    @GetMapping("/posts/save")
    public String postsSave() {
        return "posts-save";
    }
}
