package com.ziggs.ziggs_backend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
public class AlertDTO {

    private Long alertId;
    private String filePath;
    private Boolean threatFlag;
    private Date timestamp;
}
