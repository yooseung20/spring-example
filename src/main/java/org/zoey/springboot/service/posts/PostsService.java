package org.zoey.springboot.service.posts;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.zoey.springboot.domain.posts.Posts;
import org.zoey.springboot.domain.posts.PostsRepository;
import org.zoey.springboot.web.dto.PostsResponseDto;
import org.zoey.springboot.web.dto.PostsSaveRequestDto;
import org.zoey.springboot.web.dto.PostsUpdateRequestDto;


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

        @Transactional
        public Long update(Long id, PostsUpdateRequestDto requestDto) {
                Posts posts = postsRepository.findById(id).orElseThrow(() ->
                        new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));

                posts.update(requestDto.getTitle(), requestDto.getContent());
                return id;
        }

        public PostsResponseDto findById (Long id) {
                Posts entity = postsRepository.findById(id).orElseThrow(() ->
                                new IllegalArgumentException("해당 게시글이 없습니다. id= " + id));
                return new PostsResponseDto(entity);
        }
}
