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
public class RegisterRequestModel {

    @NotEmpty(message = "The first name is required.")
    @Size(min = 2, max = 30, message = "The length of first name must be between 2 and 30 characters.")
    private String firstName;

    @NotEmpty(message = "The last name is required.")
    @Size(min = 2, max = 30, message = "The length of last name must be between 2 and 30 characters.")
    private String lastName;

    @NotEmpty(message = "The email address is required.")
    @Email(message = "The email address is invalid.")
    private String email;

    @Size(min=5, max=15, message="{that password is not valid password must be between 5 and 15 characters}")
    private String password;

    @NotEmpty(message = "The phoneNumber is required.")
    @Size(min = 11, max = 15, message = "The length of phoneNumber must be between 11 and 15 Number.")
    @Pattern(regexp = "^[0-9]+$",message = "You Can Only Write Numbers")
    private String phoneNumber;

    @NotEmpty(message = "The address is required.")
    private String address;
}
