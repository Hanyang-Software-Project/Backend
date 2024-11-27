package com.ziggs.ziggs_backend.dto;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class UserCreateDTO {

    @NotBlank(message = "Username is mandatory.")
    private String username;

    @Email(message = "Invalid email.")
    @NotBlank(message = "Email is mandatory.")
    private String email;

    @NotBlank(message = "Phone number is mandatory.")
    @Pattern(regexp = "^[+]?[0-9]{7,15}$", message = "Invalid phone number format.")
    private String phoneNumber;

    @NotBlank(message = "Password is mandatory.")
    private String password;

    @NotBlank(message = "House name is mandatory.")
    private String houseName;

    @NotBlank(message = "House address is mandatory.")
    private String address;
}
