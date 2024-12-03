package com.ziggs.ziggs_backend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class SecureDTO {
    String message;
    String uid;
    String role;
}
