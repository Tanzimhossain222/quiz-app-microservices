package com.star.question_Service;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HelloController {
    @RequestMapping("/hello")
    public String sayHello() {
        return "Hello, World!";
    }
}
