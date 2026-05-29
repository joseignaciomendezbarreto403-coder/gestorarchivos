package com.example.gestorarchivos.service;

import com.example.gestorarchivos.Repository.FileRepository;
import com.example.gestorarchivos.model.FileMeta;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.*;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class FileService {

    private final Path root = Paths.get("uploads");
    private final FileRepository repository;

    public FileService(FileRepository repository) throws IOException {

        this.repository = repository;

        if (!Files.exists(root)) {
            Files.createDirectories(root);
        }
    }

    public String save(MultipartFile file) throws IOException {

        String filename = file.getOriginalFilename();

        if (filename == null || filename.isBlank()) {
            throw new RuntimeException("Nombre de archivo inválido");
        }

        Path destination = root.resolve(filename);

        Files.copy(
                file.getInputStream(),
                destination,
                StandardCopyOption.REPLACE_EXISTING
        );

        FileMeta meta = new FileMeta(
                filename,
                file.getSize(),
                LocalDateTime.now()
        );

        FileMeta savedMeta = repository.saveAndFlush(meta);

        return savedMeta.getFilename();
    }

    public List<FileMeta> list() {
        return repository.findAll();
    }

    public boolean delete(String filename) throws IOException {

        Path path = root.resolve(filename);

        boolean deleted = Files.deleteIfExists(path);

        repository.findByFilename(filename)
                .ifPresent(fileMeta -> repository.delete(fileMeta));

        return deleted;
    }
}