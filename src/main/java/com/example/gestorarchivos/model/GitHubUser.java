package com.example.gestorarchivos.model;

import lombok.Data;

@Data
public class GitHubUser {
    private String login;
    private String name;
    private String bio;
    private String avatar_url;
    private int public_repos;
}
