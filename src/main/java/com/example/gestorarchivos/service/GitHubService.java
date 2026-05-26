package com.example.gestorarchivos.service;

import com.example.gestorarchivos.model.GitHubUser;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class GitHubService {

    private static final String GITHUB_API_URL = "https://api.github.com/users/";

    public GitHubUser getUserInfo(String username) {
        try {
            RestTemplate restTemplate = new RestTemplate();
            return restTemplate.getForObject(GITHUB_API_URL + username, GitHubUser.class);
        } catch (Exception e) {
            System.out.println("❌ Error al obtener usuario de GitHub: " + e.getMessage());
            return null;
        }
    }
}
