package com.wealth.demo.model.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class UserRegisterDTO {

    @NotBlank(message = "{UserRegisterDTO.username.notNull}")
    @Size(min = 3, max = 50, message = "{UserRegisterDTO.username.size}")
    private String username;

    @NotBlank(message = "{UserRegisterDTO.password.notNull}")
    @Size(min = 6, message = "{UserRegisterDTO.password.size}")
    private String password;

    @NotBlank(message = "{UserRegisterDTO.email.notNull}")
    @Email(message = "{UserRegisterDTO.email.format}")
    private String email;

}
