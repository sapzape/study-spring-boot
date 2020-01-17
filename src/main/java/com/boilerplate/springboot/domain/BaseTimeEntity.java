package com.boilerplate.springboot.domain;

import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@Getter
// JPA Entity 클래스들이 해당 클래스 상속할 경우 필드들도 칼럼으로 인식하도록 함
@MappedSuperclass
// Auditing 기능 포함
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseTimeEntity {

    // 생성시 시간 자동 저장
    @CreatedDate
    private LocalDateTime createdDate;

    // 변경시 시간 자동 저장
    @LastModifiedDate
    private LocalDateTime modifiedDate;
}
