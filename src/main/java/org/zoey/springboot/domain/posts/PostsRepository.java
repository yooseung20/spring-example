package org.zoey.springboot.domain.posts;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

// db 접근할 수 있는 jparepository 생성
// JpaRepository <Entity 클래스, PK타입>을 상속하면 기본적인 CRUD 메소드가 생성된다.
public interface PostsRepository extends JpaRepository<Posts, Long>{
    @Query("SELECT p FROM Posts p ORDER BY p.id DESC")
    List<Posts> findAllDesc();
}

// Entity 클래스와 Entity Repository 는 짝궁
// 도메인 패키지에서 함께 관리
