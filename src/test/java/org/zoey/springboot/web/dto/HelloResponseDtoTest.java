package org.zoey.springboot.web.dto;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class HelloResponseDtoTest {

    @Test
    public void 롬복_기능_테스트(){
        String name="test";
        int amount=100;

        HelloResponseDto dto=new HelloResponseDto(name,amount);

        //assertThat은 assertj라는 테스트 검증 라이브러리의 검증 메소드이다.
        //검증하고 싶은 대상을 메소드 인자로 받는다
        //메소드 체이닝이 지원되어 isEqualTo와 같이 메소드를 이어서 사용할 수 있다.
        //isEqualTo는 assertj의 동등 비교 메소드이다
        //assertThat에 있는 값과 isEqualTo의 값을 비교해서 같을 때만 성공한다.
        assertThat(dto.getName()).isEqualTo(name);
        assertThat(dto.getAmount()).isEqualTo(amount);
    }
}

