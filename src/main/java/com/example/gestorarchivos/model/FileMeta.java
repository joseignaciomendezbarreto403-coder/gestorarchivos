package com.example.gestorarchivos.model;

import java.time.LocalDateTime;

public class FileMeta {
    private Long id;
    private String filename;
    private long size;
    private LocalDateTime uploadDate;

    public FileMeta() {}

    public FileMeta(Long id, String filename, long size, LocalDateTime uploadDate) {
        this.id = id;
        this.filename = filename;
        this.size = size;
        this.uploadDate = uploadDate;
    }

    // getters y setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getFilename() { return filename; }
    public void setFilename(String filename) { this.filename = filename; }
    public long getSize() { return size; }
    public void setSize(long size) { this.size = size; }
    public LocalDateTime getUploadDate() { return uploadDate; }
    public void setUploadDate(LocalDateTime uploadDate) { this.uploadDate = uploadDate; }
}
