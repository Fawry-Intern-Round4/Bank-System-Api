package com.sakr.banksystemapi.model.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthenticationRequestModel {
    @NotEmpty(message = "The email address is required.")
    @Email(message = "The email address is invalid.")
    private String email;

    @Size(min=5, max=15, message="{that password is not valid password must be between 5 and 15 characters}")
    private String password;
}
