package com.boilerplate.springboot.web;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

// 테스트를 진행할 때 JUnit에 내장된 실행자 외 다른 실행자 실행
@RunWith(SpringRunner.class)
// Spring MVC 테스트 어노테이션 (@Controller, @ControllerAdvice 사용 가능)
@WebMvcTest
public class HelloControllerTest {

    // 스프링이 관리하는 Bean 주입 받음
    @Autowired
    // 스프링 MVC 테스트의 시작점
    private MockMvc mvc;

    @Test
    public void hello가_리턴된다() throws Exception {
        String hello = "hello";

        mvc.perform(get("/hello"))
                .andExpect(status().isOk())
                .andExpect(content().string(hello));
    }
}
