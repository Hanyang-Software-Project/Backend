package com.ziggs.ziggs_backend.dto;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
public class UserDTO {
    private Long id;
    private String username;
    private String email;
    private Date createdAt;

}
