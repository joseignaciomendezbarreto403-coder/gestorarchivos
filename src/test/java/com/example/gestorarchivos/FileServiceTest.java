package com.example.gestorarchivos;

import com.example.gestorarchivos.service.FileService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockMultipartFile;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class FileServiceTest {

    private FileService fileService;

    @BeforeEach
    void setUp() throws IOException {
        fileService = new FileService();
    }

    @Test
    void testSaveFile() throws IOException {
        MockMultipartFile file = new MockMultipartFile("file", "test.txt",
                "text/plain", "Hola Mundo".getBytes());

        String filename = fileService.save(file);
        assertEquals("test.txt", filename, "El nombre del archivo debería coincidir");
        assertTrue(fileService.list().contains("test.txt"), "El archivo debería estar listado");
    }

    @Test
    void testDeleteFile() throws IOException {
        MockMultipartFile file = new MockMultipartFile("file", "delete.txt",
                "text/plain", "Eliminar".getBytes());
        fileService.save(file);

        boolean deleted = fileService.delete("delete.txt");
        assertTrue(deleted, "El archivo debería eliminarse correctamente");
        assertFalse(fileService.list().contains("delete.txt"), "El archivo no debería aparecer en la lista");
    }
}
