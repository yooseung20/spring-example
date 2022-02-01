package org.zoey.springboot.web.dto;


import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
// 파라미터가 없는 기본 생성자를 생성
// @RequiredArgsConstructor : final이나 @NonNull인 필드 값만 파라미터로 받는 생성자 만듦
@NoArgsConstructor
public class PostsUpdateRequestDto {
    private String title;
    private String content;

    @Builder
    public PostsUpdateRequestDto(String title, String content){
        this.title = title;
        this.content = content;
    }

}

