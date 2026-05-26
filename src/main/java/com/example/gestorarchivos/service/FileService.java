package com.example.gestorarchivos.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.*;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FileService {

    private final Path root = Paths.get("uploads");

    public FileService() throws IOException {
        if (!Files.exists(root)) {
            Files.createDirectories(root);
        }
    }

    public String save(MultipartFile file) throws IOException {
        Path destination = root.resolve(file.getOriginalFilename());
        Files.copy(file.getInputStream(), destination, StandardCopyOption.REPLACE_EXISTING);
        return file.getOriginalFilename();
    }

    public List<String> list() throws IOException {
        return Files.list(root)
                .map(path -> path.getFileName().toString())
                .collect(Collectors.toList());
    }

    public boolean delete(String filename) throws IOException {
        return Files.deleteIfExists(root.resolve(filename));
    }
}
