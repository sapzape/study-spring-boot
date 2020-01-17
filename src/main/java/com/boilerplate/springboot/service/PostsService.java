package com.boilerplate.springboot.service;

import com.boilerplate.springboot.domain.posts.Posts;
import com.boilerplate.springboot.domain.posts.PostsRepository;
import com.boilerplate.springboot.web.dto.PostsResponseDto;
import com.boilerplate.springboot.web.dto.PostsSaveRequestDto;
import com.boilerplate.springboot.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@RequiredArgsConstructor
@Service
public class PostsService {
    private final PostsRepository postsRepository;

    @Transactional
    public Long save(PostsSaveRequestDto requestsDto) {
        return postsRepository.save(requestsDto.toEntity()).getId();
    }

    @Transactional
    public Long update(Long id, PostsUpdateRequestDto requestDto) {
        Posts posts = postsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 사용자가 없습니다. id = " + id));

        //트랜잭션이 끝나는 시점에 변경분 반영(dirty checking)
        posts.update(requestDto.getTitle(), requestDto.getContent());

        return id;
    }

    public PostsResponseDto findById (Long id) {
        Posts entity = postsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 사용자가 없습니다. id=" + id));

        return new PostsResponseDto(entity) ;
    }
}
