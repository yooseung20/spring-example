package org.zoey.springboot.domain.user;

// 각 사용자의 권한을 관리할 Eunm 클래스 Role을 생성한다.

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
// 초기화 되지않은 final 필드나, @NonNull 이 붙은 필드에 대해 생성자를 생성해 준다.
@RequiredArgsConstructor
public enum Role {

    // 스프링 시큐리티에서는 권한 코드에 항상 ROLE이 앞에 있어야 한다.
    // 생성자
    GUEST("ROLE_GEUST", "손님"),
    USER("ROLE_USER", "일반사용자");

    private final String key;
    private final String title;



}
