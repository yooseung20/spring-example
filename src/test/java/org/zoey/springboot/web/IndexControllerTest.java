// 페이지와 관련된 컨트롤러는 모두 indexCon

package org.zoey.springboot.web;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class IndexControllerTest {
    @Autowired
    // controller test 할 때 활용
    // 서블릿 컨테이너 실행
    // 클라이언트 입장에서 사용할 때 문제가 없는지 확인
    // MockMvc도 controller test 할때 활용하지만, 컨테이너를 실행하지 않고
    // 서버의 입장에서 구현한 api를 통해 비지니스로직에 문제가 없는지 테스트
    private TestRestTemplate restTemplate;

    @Test
    public void 메인페이지_로딩(){
        // when
        // testRestTemplate의 getForObject(URL, Return Type)
        String body = this.restTemplate.getForObject("/", String.class);

        //then
        assertThat(body).contains("스프링 부트로 시작하는 웹 서비스");

    }

}
