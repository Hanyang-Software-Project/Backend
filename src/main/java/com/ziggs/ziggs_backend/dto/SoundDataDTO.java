package com.ziggs.ziggs_backend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class SoundDataDTO {
    private String filePath;
    private Long deviceId;
    private Long userId;
}

