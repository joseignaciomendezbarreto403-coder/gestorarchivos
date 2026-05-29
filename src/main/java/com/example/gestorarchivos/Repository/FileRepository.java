package com.example.gestorarchivos.Repository;

import com.example.gestorarchivos.model.FileMeta;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface FileRepository extends JpaRepository<FileMeta, Long> {
    Optional<FileMeta> findByFilename(String filename);
}