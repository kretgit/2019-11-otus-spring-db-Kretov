package ru.otus.homework.dbpractice.security;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = {"http://localhost:3000"})
@RestController
public class SecurityController {

    @RequestMapping(value = "basicauth")
    public AuthenticationBean authenticate() {
        return new AuthenticationBean("Welcome to library");
    }

}
