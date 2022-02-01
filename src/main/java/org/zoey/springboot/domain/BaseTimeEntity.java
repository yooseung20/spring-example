package org.zoey.springboot.domain;

import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@Getter
// JPA Entity class들이 BaseTimeEntity을 상속할 경우 필드를(createdDate, modifiedDate)도 칼럼으로 인식하도록 한다.
@MappedSuperclass
// BaseTimeEntity 클래스에 Auditing 기능을 포함시킨다.
// Spring Data JPA에서 시간에 대해서 자동으로 값을 넣어주는 기능
// audit을 이용하면 자동으로 시간을 매핑하여 데이터베이스의 테이블에 넣어준다.
@EntityListeners(AuditingEntityListener.class)
public class BaseTimeEntity {

    // Entity가 생성되어 저장될 때 시간이 자동 저장된다.
    @CreatedDate
    private LocalDateTime createdDate;

    // 조회한 Entity의 값을 변경할 때 시간이 자동 저장된다.
    @LastModifiedDate
    private LocalDateTime modifiedDate;
}
