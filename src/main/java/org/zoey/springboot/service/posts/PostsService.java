package org.zoey.springboot.service.posts;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.zoey.springboot.domain.posts.Posts;
import org.zoey.springboot.domain.posts.PostsRepository;
import org.zoey.springboot.web.dto.PostsListResponseDto;
import org.zoey.springboot.web.dto.PostsResponseDto;
import org.zoey.springboot.web.dto.PostsSaveRequestDto;
import org.zoey.springboot.web.dto.PostsUpdateRequestDto;

import java.util.List;
import java.util.stream.Collectors;


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

        // transactional readOnly option
        // 트랜잭션 범위는 유지하되, 조회기능만 남겨둠
        // 조회 속도가 개선 -> 등록, 수정, 삭제 기능이 없는 서비스 메소드에 사용추천
        // 읽기 전용
        @Transactional(readOnly = true) // 읽기 전용
        public List<PostsListResponseDto> findAllDesc() {
                return postsRepository.findAllDesc().stream()
                        // map(posts -> new PostListResponseDto(posts))
                        // Posts stream을  map을 통해 PostListResponseDto로 변환
                        .map(PostsListResponseDto::new)
                        // list로 반환
                        .collect(Collectors.toList());

        }

        @Transactional
        public void delete (Long id){
                Posts posts = postsRepository.findById(id)
                        .orElseThrow(() ->  new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));
                // JpaRepository delete 메소드 지원
                postsRepository.delete(posts);

        }
}
