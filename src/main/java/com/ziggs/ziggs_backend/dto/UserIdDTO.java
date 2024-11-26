package com.ziggs.ziggs_backend.dto;

public class UserIdDTO {

    private Long userId;

    public UserIdDTO(Long userId) {
        this.userId = userId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
