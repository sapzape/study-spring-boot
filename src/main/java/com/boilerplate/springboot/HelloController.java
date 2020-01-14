package com.boilerplate.springboot;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

// JSON을 반환하는 컨트롤러
@RestController
public class HelloController {

    // Get 요청을 받을 수 있는 API 생성
    @GetMapping("/hello")
    public String hello() {
        return "hello";
    }
}
