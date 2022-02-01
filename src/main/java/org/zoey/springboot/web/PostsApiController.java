package org.zoey.springboot.web;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.zoey.springboot.service.posts.PostsService;
import org.zoey.springboot.web.dto.PostsResponseDto;
import org.zoey.springboot.web.dto.PostsSaveRequestDto;
import org.zoey.springboot.web.dto.PostsUpdateRequestDto;

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

    //PostMapping, getMapping
    @PutMapping("/api/v1/posts/{id}")

    // @RequestParam vs @PathVariable
    // 데이터를 string 형대로 전달(GET방식) vs REST 방식(1개의 변수만 사용가능)
    public Long update(@PathVariable Long id, @RequestBody PostsUpdateRequestDto requestDto){
        return postsService.update(id, requestDto);
    }
    @GetMapping("/api/v1/posts/{id}")
    public PostsResponseDto findById (@PathVariable Long id){
        return postsService.findById(id);
    }
}
