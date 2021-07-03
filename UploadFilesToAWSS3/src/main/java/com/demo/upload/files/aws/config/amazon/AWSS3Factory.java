package com.demo.upload.files.aws.config.amazon;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.*;
import com.amazonaws.util.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;

@Service
public class AWSS3Factory {

    @Autowired
    private AmazonS3 amazonS3Client;

    @Value("${s3.bucket.name}")
    private String defaultBucketName;

    @Value("${s3.folder}")
    private String folder;

    public void uploadFileToS3Bucket(File file) {
        PutObjectRequest putObjectRequest = new PutObjectRequest(defaultBucketName, folder + "/" + file.getName(), file);
        PutObjectResult putObjectResult = amazonS3Client.putObject(putObjectRequest);
        if (putObjectResult == null) {
            throw new RuntimeException("Error occurred while uploading file");
        }
    }

    public byte[] getFileFromS3Bucket(String key) {
        S3Object obj = amazonS3Client.getObject(defaultBucketName, folder + "/" + key);
        S3ObjectInputStream stream = obj.getObjectContent();
        try {
            byte[] content = IOUtils.toByteArray(stream);
            obj.close();
            return content;
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
