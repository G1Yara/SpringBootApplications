package com.demo.upload.files.aws.service;


import com.demo.upload.files.aws.config.amazon.AWSS3Factory;
import com.demo.upload.files.aws.dto.FileUploadDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;

@Service
public class FileUploadService {

    @Autowired
    private AWSS3Factory awss3Factory;

    @Async
    public FileUploadDto uploadFile(MultipartFile[] multipartFiles) {
        String message = "File Uploaded successfully";

        List<File> files = new ArrayList<>();
        for (MultipartFile multipartFile : multipartFiles) {
            File file = convertMultiPartFileToFile(multipartFile);
            files.add(file);
        }
        List<String> fileNames = new ArrayList<>();
        for (File file : files) {
            awss3Factory.uploadFileToS3Bucket(file);
            fileNames.add(file.getName());
        }
        return new FileUploadDto(fileNames, message);
    }

    private File convertMultiPartFileToFile(final MultipartFile multipartFile) {
        final File file = new File(Objects.requireNonNull(multipartFile.getOriginalFilename()));
        if (file.getName().length() == 0) {
            throw new RuntimeException("File is missing");
        }
        try (final FileOutputStream outputStream = new FileOutputStream(file)) {
            outputStream.write(multipartFile.getBytes());
        } catch (final IOException ex) {
            throw new RuntimeException("Error converting the multi-part file to file= " + ex.getMessage());
        }
        return file;
    }

    public byte[] downloadFile(String key) {
        return awss3Factory.getFileFromS3Bucket(key);
    }
}
