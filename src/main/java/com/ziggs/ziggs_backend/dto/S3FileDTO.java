package com.ziggs.ziggs_backend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class S3FileDTO {
    private String message;
    private String fileName;
}
