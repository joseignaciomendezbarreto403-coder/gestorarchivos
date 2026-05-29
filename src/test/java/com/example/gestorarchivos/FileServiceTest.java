package com.example.gestorarchivos;

import com.example.gestorarchivos.Repository.FileRepository;
import com.example.gestorarchivos.model.FileMeta;
import com.example.gestorarchivos.service.FileService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockMultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class FileServiceTest {

    private FileService FileService;
    private FileRepository Repository;

    @BeforeEach
    void setUp() throws IOException {

        Repository = mock(FileRepository.class);

        FileService = new FileService(Repository);
    }

    @Test
    void testSaveFile() throws IOException {

        MockMultipartFile file = new MockMultipartFile(
                "file",
                "test.txt",
                "text/plain",
                "Hola Mundo".getBytes()
        );

        FileMeta meta = new FileMeta(
                "test.txt",
                file.getSize(),
                LocalDateTime.now()
        );

        when(Repository.saveAndFlush(any(FileMeta.class)))
                .thenReturn(meta);

        String filename = FileService.save(file);

        assertEquals("test.txt", filename);

        verify(Repository, times(1))
                .saveAndFlush(any(FileMeta.class));
    }

    @Test
    void testDeleteFile() throws IOException {

        FileMeta meta = new FileMeta(
                "delete.txt",
                0,
                LocalDateTime.now()
        );

        when(Repository.findByFilename("delete.txt"))
                .thenReturn(Optional.of(meta));

        boolean deleted = FileService.delete("delete.txt");

        assertNotNull(deleted);

        verify(Repository, times(1))
                .findByFilename("delete.txt");

        verify(Repository, times(1))
                .delete(meta);
    }
}