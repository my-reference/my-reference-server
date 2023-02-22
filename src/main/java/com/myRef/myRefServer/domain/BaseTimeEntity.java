package com.myRef.myRefServer.domain;

import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;


import java.time.LocalDateTime;


/**
 * BaseTimeEntity 클래스의 Auditing 기능으로 인해서 트랜잭션 커밋 시점에 플러시가 호출할 때 하이버네이트가 자동으로 시간 값을 채워주는것을 확인 할 수 있음
 * 스프링 부트의 Entry 포인트인 실행 클래스에 @EnableJpaAuditing 어노테이션을 적용하여 JPA Auditing을 활성화 해야하는 것을 잊지 말아야 합니다.
 */

@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)  //
public class BaseTimeEntity {

    @CreatedDate
    private LocalDateTime createDate;

    @LastModifiedDate
    private LocalDateTime modifiedDate;

}
