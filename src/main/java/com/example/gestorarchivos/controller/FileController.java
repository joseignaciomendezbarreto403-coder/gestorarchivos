package com.example.gestorarchivos.controller;

import com.example.gestorarchivos.model.GitHubUser;
import com.example.gestorarchivos.service.FileService;
import com.example.gestorarchivos.service.GitHubService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;


@Controller
public class FileController {

    @Autowired
    private FileService fileService;

    @Autowired
    private GitHubService gitHubService;

    
    @GetMapping("/")
public String index(Model model) {
    model.addAttribute("files", fileService.list());
    return "index";
}

    
    @PostMapping("/upload")
    public String upload(@RequestParam("file") MultipartFile file) {
        try {
            fileService.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "redirect:/";
    }

    
    @GetMapping("/delete/{filename}")
public String delete(@PathVariable("filename") String filename, Model model) {
    try {
        String decodedFilename = java.net.URLDecoder.decode(filename, java.nio.charset.StandardCharsets.UTF_8.name());
        boolean deleted = fileService.delete(decodedFilename);

        if (!deleted) {
            model.addAttribute("error", "⚠️ El archivo no existe o no pudo eliminarse.");
        }

    } catch (IOException e) {
        e.printStackTrace();
        model.addAttribute("error", "❌ Error al eliminar el archivo: " + e.getMessage());
    } catch (Exception e) {
        e.printStackTrace();
        model.addAttribute("error", "⚠️ Error inesperado: " + e.getMessage());
    }

    return "redirect:/";
}


    @GetMapping("/github/{username}")
    public String getGitHubUser(@PathVariable String username, Model model) {
        GitHubUser user = gitHubService.getUserInfo(username);
        model.addAttribute("user", user);
        return "github-user";
    }
}
