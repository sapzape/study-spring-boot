package com.boilerplate.springboot.domain.posts;

import org.springframework.data.jpa.repository.JpaRepository;

// <Entity 클래스, PK 타입> 상속시, 기본적인 CRUD 메소드 자동 생성
public interface PostRepository extends JpaRepository<Posts, Long> {
}
