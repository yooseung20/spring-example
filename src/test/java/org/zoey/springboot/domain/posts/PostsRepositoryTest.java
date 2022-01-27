package org.zoey.springboot.domain.posts;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


@RunWith(SpringRunner.class)
@SpringBootTest
public class PostsRepositoryTest {

    //스프링이 관리하는 Bean을 주입 받는다.
    @Autowired
    PostsRepository postsRepository;

    // junit에서 단위 테이트가 끝날 때 마다 수행되는 메소드를 지정한다.
    // 보통은 배포 전 전체 테스트를 수행할 때 테스트간 데이터 침범을 막기 위해 사용한다.
    // 여러 테스트가 동시에 수행되면 테스트용 데이터베이스인 H2에 데이터가 그대로 남아있어 다음 테스트 실행시 테스가 실패 할 수 있다.
    @After
    public void cleanup(){
        postsRepository.deleteAll();
    }

    @Test
    public void 게시글저장_불러오기(){
        //given (삽입할 데이터)
        String title = "테스트 게시글";
        String content = "테스트 본문";

        // postRepository.save
        // 테이블 posts에 insert/update 쿼리를 실행한다.
        // id값이 있다면 update, 없다면 insert
        postsRepository.save(Posts.builder()
                .title(title)
                .content(content)
                .author("jojoldu@gmail.com")
                .build());

        //when
        // 테이블 posts에 있는 모든 데이터를 조회해오는 메소드이다.
        List<Posts> postsList = postsRepository.findAll();

        //then
        Posts posts =  postsList.get(0);
        assertThat(posts.getTitle()).isEqualTo(title);
        assertThat(posts.getContent()).isEqualTo(content);
    }
}
