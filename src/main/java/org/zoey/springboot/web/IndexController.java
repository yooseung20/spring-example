package org.zoey.springboot.web;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PathVariable;
import org.zoey.springboot.config.auth.LoginUser;
import org.zoey.springboot.config.auth.dto.SessionUser;
import org.zoey.springboot.service.posts.PostsService;
import org.zoey.springboot.web.dto.PostsResponseDto;

import javax.servlet.http.HttpSession;

// jSON을 반환하는 Controller로 만들어준다 .
@RequiredArgsConstructor
@Controller
public class IndexController {
    private final PostsService postsService;
    private final HttpSession httpSession;

    @GetMapping ("/")
    // Model
    // 서버 템플릿 엔진에서 사용할 수 있는 객체를 저장 할 수 있다.
    // 여기서는 posts.Service.findAllDesc()로 가져온 결과를 posts로 index.mustache에 전달한다.
    public String index(Model model, @LoginUser SessionUser user){
        model.addAttribute("posts", postsService.findAllDesc());

        // CustomOAuthUserService에서 로그인 성공시 sessionUser를 저장
        // 즉, 로그인 성공 시 httpSession.getAttribute("user")에서 값을 가져올 수 있다.
        //SessionUser user = (SessionUser) httpSession.getAttribute("user");
        // 어느 컨트롤러든지 @LoginUser만 사용하면 세션 정보를 가져올 수 있다.

        // if (user != null)
        // 세션에 저장된 값이 있을 때만 model userName으로 등록한다.
        // 세션에 저장된 값이 없으면 model엔 아무런 값이 없는 상태이므로, 로그인 버튼이 보임
        if (user!=null){
            model.addAttribute("userName", user.getName());
        }
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
