package com.ziggs.ziggs_backend.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
public class DeviceDTO {
    @NotNull
    private String deviceName;

    @NotNull
    private Long userId;

}
