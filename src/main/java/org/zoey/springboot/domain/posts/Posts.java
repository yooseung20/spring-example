package org.zoey.springboot.domain.posts;


import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.zoey.springboot.domain.BaseTimeEntity;


import javax.persistence.*;


//lombok
@Getter // 클래스 내 모든 필드의 Getter 메소드를 자동생성
@NoArgsConstructor // 기본생성자 자동추가 public Post(){}

// 테이블과 연결될 클래스임을 나타낸다
// 테이블명은 클래스명을 언더스코어 네이밍(_)으로 바꾼것을 매칭한다.
// EX> SalesManager.java -> sales_manager table

@Entity
public class Posts extends BaseTimeEntity {

    // 해당 테이블에 PK 필드를 나타낸다.
    @Id
    // PK 생성규칙을 나타낸다.
    // GenerationTpye.IDENTITY 를 추가해야만 auto_increment가 가능하다.
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    //entity의 pk는 long 타입의 auto_increment가 적절하다.
    private Long id;

    // 테이블의 컬럼을 나타낸다.(선언하지 않아도 해당클래스의 필드는 모두 컬럼으로 인식)
    // 기본값 외에 추가로 변경이 필요한 옵션이있으면 사용한다.
    // 문자열의 경우 VARCHAR(255)가 기본값인데, 사이즈를 500으로 늘리거나, 타입을 TEXT로 바꾸고 싶은 경우
    @Column(length = 500, nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;
    private String author;

    // 해당 클래스의 빌더 패턴 클래스를 생성
    // 생성자 상단에 선언 시 생성자에 포함된 필드만 빌더에 포함
    @Builder
    public Posts(String title, String content, String author){
        this.title = title;
        this.content = content;
        this.author = author;
    }

    public void update(String title, String content){
        this.title = title;
        this.content = content;
    }
}
