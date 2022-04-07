package com.ptrkalm.githubusersapi.user;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/users")
public class UserController {
    private final RestTemplate restTemplate = new RestTemplate();

    @GetMapping("{login}")
    public User getUser(@PathVariable("login") String login) throws JsonProcessingException {
        String uri = "https://api.github.com/users/" + login;
        String json = restTemplate.getForObject(uri, String.class);
        return User.fromJson(json);
    }
}
