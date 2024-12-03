package com.ziggs.ziggs_backend.service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;

@Service
public class S3Service {

    private final AmazonS3 amazonS3;
    private final String bucketName;

    public S3Service(@Qualifier("amazonS3Client") AmazonS3 amazonS3, @Value("${amazon.bucket-name}") String bucketName) {
        this.amazonS3 = amazonS3;
        this.bucketName = bucketName;
    }

    public String uploadFile(MultipartFile file) throws IOException {
        // Specify the folder name
        String folderName = "wav/";

        // Construct the full path for the file
        String fileName = folderName + System.currentTimeMillis() + "_" + (file.getOriginalFilename() != null ? file.getOriginalFilename() : "default_file");

        // Set metadata for the file
        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentLength(file.getSize());
        metadata.setContentType(file.getContentType());

        // Upload the file directly from InputStream
        amazonS3.putObject(bucketName, fileName, file.getInputStream(), metadata);

        return "File uploaded successfully to folder '" + folderName + "': " + fileName;
    }
}
