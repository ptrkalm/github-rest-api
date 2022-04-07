package com.ptrkalm.githubusersapi.user;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class User {
    private long id;
    private String login;
    private String name;
    private String type;
    private String avatarUrl;
    private String createdAt;
    private Double calculations;

    public static User fromJson(String json) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode rootNode = mapper.readTree(json);

        return new User(
                rootNode.findValue("id").asInt(),
                rootNode.findValue("login").asText(),
                rootNode.findValue("name").asText(),
                rootNode.findValue("type").asText(),
                rootNode.findValue("avatar_url").asText(),
                rootNode.findValue("created_at").asText(),
                6.0 / rootNode.findValue("followers").asDouble() * (2.0 + rootNode.findValue("public_repos").asDouble())
        );
    }
}
