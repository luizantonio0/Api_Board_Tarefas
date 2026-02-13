package com.tarefas.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("auth")
public class AuthController {
    @PostMapping("/login")
    public String login(@RequestBody String login, String password){
        return "{\n" +
                "  \"token\": \"dummy-jwt-token\",\n" +
                "  \"user\": {\n" +
                "    \"id\": \"1\",\n" +
                "    \"name\": \"Test User\",\n" +
                "    \"email\": \"email@ex.com\",\n" +
                "    \"login\": \"test\",\n" +
                "    \"role\": \"ADMIN\"\n" +
                "  }\n" +
                "}";
    }

}
