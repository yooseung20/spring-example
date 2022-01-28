package org.zoey.springboot.web;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.zoey.springboot.service.posts.PostsService;
import org.zoey.springboot.web.dto.PostsSaveRequestDto;

// 초기화 되지않은 final 필드나, @NonNull이 붙은 필드에 대해 생성자를 생성해 준다.
// 주로 의존성 주입 편의성을 위해 사용된다.
@RequiredArgsConstructor
// cf. @NoArgsConstructor 기본생성자 생성

// jSON을 반환하는 Controller로 만들어준다 .
@RestController
public class PostsApiController {
    private final PostsService postsService;

    // cf.GetMapping
    @PostMapping("/api/v1/posts")

    // @Requestparam vs @RequestBody
    public Long save(@RequestBody PostsSaveRequestDto requestDto) {

        return postsService.save(requestDto);
    }

}
