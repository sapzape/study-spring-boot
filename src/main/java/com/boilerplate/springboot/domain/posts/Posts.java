package com.boilerplate.springboot.domain.posts;

import com.boilerplate.springboot.domain.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

// 클래스 내 모든 필드의 Getter 메소드 자동 생성
@Getter
// 기본 생성자 자동 추가
@NoArgsConstructor
// 테이블과 링크될 클래스
@Entity
public class Posts extends BaseTimeEntity {
    // 해당 테이블의 PK 필드
    @Id
    // PK 생성 규칙 (GenerationType.IDENTITY -> auto_increment)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 테이블 컬럼. 보통 생략하지만, 기본값 외 추가로 변경할 옵션이 있을 경우 사용
    @Column(length = 500, nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    private String author;

    // 빌더 패턴 클래스 생성
    // 생성자 상단에 선언 시 생성자에 포함된 필드만 빌더에 포함
    @Builder
    public Posts(String title, String content, String author) {
        this.title = title;
        this.content = content;
        this.author = author;
    }

    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
