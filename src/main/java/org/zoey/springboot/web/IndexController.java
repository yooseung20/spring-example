package org.zoey.springboot.web;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PathVariable;
import org.zoey.springboot.service.posts.PostsService;
import org.zoey.springboot.web.dto.PostsResponseDto;

// jSON을 반환하는 Controller로 만들어준다 .
@RequiredArgsConstructor
@Controller
public class IndexController {
    private final PostsService postsService;
    @GetMapping ("/")
    // Model
    // 서버 템플릿 엔진에서 사용할 수 있는 객체를 저장 할 수 있다.
    // 여기서는 posts.Service.findAllDesc()로 가져온 결과를 posts로 index.mustache에 전달한다.
    public String index(Model model){
        model.addAttribute("posts", postsService.findAllDesc());
        return "index";
    }

    // posts/save를 호출하면 posts-save.mustache
    @GetMapping("/posts/save")
    public String postsSave() {

        return "posts-save";
    }

    @GetMapping("/posts/update/{id}")
    public String postsUpdate(@PathVariable Long id, Model model){
        PostsResponseDto dto = postsService.findById(id);
        model.addAttribute("post", dto);
        return "posts-update";
    }
}
