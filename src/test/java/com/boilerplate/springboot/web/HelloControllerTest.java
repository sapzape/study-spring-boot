package com.boilerplate.springboot.web;

import com.boilerplate.springboot.HelloController;
import com.boilerplate.springboot.config.auth.SecurityConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

// 테스트를 진행할 때 JUnit에 내장된 실행자 외 다른 실행자 실행
@RunWith(SpringRunner.class)
// Spring MVC 테스트 어노테이션 (@Controller, @ControllerAdvice 사용 가능)
// @WebMvcTest는 @Repository, @Service, @Compoent가 스캔 대상이 아니기에 스캔 대상에서 제거
@WebMvcTest(controllers = HelloController.class,
        excludeFilters = {
        @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = SecurityConfig.class)
        }
)
public class HelloControllerTest {

    // 스프링이 관리하는 Bean 주입 받음
    @Autowired
    // 스프링 MVC 테스트의 시작점
    private MockMvc mvc;

    @WithMockUser(roles = "USER")
    @Test
    public void hello가_리턴된다() throws Exception {
        String hello = "hello";

        mvc.perform(get("/hello"))
                .andExpect(status().isOk())
                .andExpect(content().string(hello));
    }

    @WithMockUser(roles = "USER")
    @Test
    public void helloDto가_리턴된다() throws Exception {
        String name = "hello";
        int amount = 1000;

        mvc.perform(get("/hello/dto")
                // String 형식의 요청 파라미터 설정
                .param("name", name)
                .param("amount", String.valueOf(amount)))
                .andExpect(status().isOk())
                // jsonPath : JSON 응답값 필드별 검증
                .andExpect(jsonPath("$.name", is(name)))
                .andExpect(jsonPath("$.amount", is(amount)));
    }
}
