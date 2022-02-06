package org.zoey.springboot.domain.user;


import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.zoey.springboot.domain.BaseTimeEntity;

import javax.persistence.*;

@Getter
// 파라미터가 없는 기본생성자 생성
@NoArgsConstructor

// JPA가 관리하는 클래스로, 해당 클래스를 엔티티라고 한다.
// JPA를 사용해서 테이블과 매핑할 클래스는 반드시 @Entity 를 붙여야 한다
@Entity
public class User extends BaseTimeEntity {

    // 해당 테이블에 PK를 알려준다.
    @Id
    // GenerationTpye.IDENTITY 를 추가해야만 auto_increment가 가능하다.
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(nullable = false)
    private Long id;

    @Column(nullable =false)
    private String name;

    @Column(nullable=false)
    private String email;

    @Column(nullable = false)
    private String picture;

    // JPA로 데이터베이스로 저장할때 enum 값을 어떤 형태로 저장할 지를 결정한다.
    // 기본적으로는 int로 된 숫자가 저장된다.
    // 숫자로 저장되면 데이터베이스로 확인할 때 그값이 무슨 코드를 의미하는지 알 수 없다.
    // 문자열(EnumType.STRING)으로 저장 될 수있도록 선언
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    // 해당 클래스의 빌더 패턴 클래스를 생성
    // 생성자 상단에 선언 시 생성자에 포함된 필드만 빌더에 포함
    @Builder
    public User(String name, String email, String picture, Role role){
        this.name = name;
        this.email = email;
        this.picture = picture;
        this.role = role;
    }

    public User update(String name, String picture){
        this.name = name;
        this.picture = picture;

        return this;
    }

    public String getRoleKey(){
        return this.role.getKey();
    }

}
