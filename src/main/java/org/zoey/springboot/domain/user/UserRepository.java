package org.zoey.springboot.domain.user;

// User의 CLUD를 책임질 UserRepository

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

// JpaRepository <Entity 클래스, PK타입>을 상속하면 기본적인 CRUD 메소드가 생성된다.
public interface UserRepository extends JpaRepository<User, Long> {
    // Optional<T>는 null이 올 수 있는 값을 감싸는 Wrapper 클래스로, 참조하더라도 NPE가 발생하지 않도록 도와준다

    // findByemail 처음가입한 사용자인지 이메일을 통해 확인한다.
    Optional<User> findByEmail(String email);



}
