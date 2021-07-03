package com.demo.upload.files.aws.dto;

import java.util.List;

public class FileUploadDto {
    private List<String> fileName;
    private String message;

    public FileUploadDto(List<String> fileName,  String message) {
        this.fileName = fileName;
        this.message = message;
    }

    public List<String> getFileName() {
        return fileName;
    }

    public void setFileName(List<String> fileName) {
        this.fileName = fileName;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
