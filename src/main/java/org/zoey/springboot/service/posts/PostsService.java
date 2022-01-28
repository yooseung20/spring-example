package org.zoey.springboot.service.posts;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.zoey.springboot.domain.posts.PostsRepository;
import org.zoey.springboot.web.dto.PostsSaveRequestDto;


// 초기화 되지않은 final 필드나, @NonNull이 붙은 필드에 대해 생성자를 생성해 준다.
@RequiredArgsConstructor

// service 역할을 하는 class임을 알린다.
@Service // 비지니스 로직을 수행한다.
public class PostsService {
        private final PostsRepository postsRepository;

        //@Transactional이 붙은 메서드는 메서드가 포함하고 있는 작업 중에 하나라도 실패할 경우 전체 작업을 취소한다
        @Transactional
        public Long save(PostsSaveRequestDto requestDto){

                return postsRepository.save(requestDto.toEntity()).getId();
        }

}
