package com.ziggs.ziggs_backend.controller;

import com.ziggs.ziggs_backend.dto.S3FileDTO;
import com.ziggs.ziggs_backend.service.S3Service;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/files")
public class FileUploadController {

    private final S3Service s3Service;

    public FileUploadController(S3Service s3Service) {
        this.s3Service = s3Service;
    }

    @PostMapping("/upload")
    public ResponseEntity<S3FileDTO> uploadFile(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return ResponseEntity.badRequest().body(new S3FileDTO("No file selected", null));
        }

        try {
            S3FileDTO response = s3Service.uploadFile(file);
            return ResponseEntity.ok().body(response);
        } catch (Exception e) {
            S3FileDTO res = new S3FileDTO("Failed to upload file: " + e.getMessage(), null);
            return ResponseEntity.internalServerError().body(res);
        }
    }
}
