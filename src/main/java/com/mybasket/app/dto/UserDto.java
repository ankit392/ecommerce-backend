package com.mybasket.app.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.mybasket.app.validations.ValidImageUrl;
import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDto {




    @NotBlank(message = "user is required")
    private String name;

    @Column(unique = true)
    @Email(message = "Required @Email.com format")
    @NotBlank(message = "email is required")
    @Size(min = 6, message = "Email must be at least 6 characters long")
    private String email;


    @Pattern(
            regexp = "^.{8,}$",
            message = "Password must be at least 8 characters long"
    )
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    @ValidImageUrl
    private String userImageUrl;
}
