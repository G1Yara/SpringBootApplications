package com.demo.upload.files.aws.controller;


import com.demo.upload.files.aws.dto.FileUploadDto;
import com.demo.upload.files.aws.service.FileUploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@CrossOrigin
@RequestMapping(path = "/file")
public class FileUploadController {

    @Autowired
    FileUploadService fileUploadService;

    @PostMapping(value = "/upload")
    public ResponseEntity<FileUploadDto> uploadFile(@RequestParam(value = "file") MultipartFile[] file) {
        return ResponseEntity.ok(fileUploadService.uploadFile(file));
    }

    @GetMapping(value = "/download/{key}")
    public ResponseEntity<Resource> downloadFile(@PathVariable("key") String key) {
        byte[] data = fileUploadService.downloadFile(key);
        ByteArrayResource resource = new ByteArrayResource(data);
        return ResponseEntity
                .ok()
                .contentLength(data.length)
                .header("Content-type", "application/octet-stream")
                .header("Content-disposition", "attachment; filename=test")
                .body(resource);
    }
}
