package com.example.userapi.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
@Entity
@Schema(description = "Details about the User")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Unique identifier of the User", example = "1")
    private Long id;

    @NotBlank(message = "Name is mandatory")
    @Schema(description = "Name of the User", example = "Azad Shukla")
    private String name;

    @Email(message = "Email should be valid")
    @NotBlank(message = "Email is mandatory")
    @Schema(description = "Email address of the User", example = "Azad@gmail.com")
    private String email;

    @Pattern(regexp = "^[+][0-9]{1,3}[0-9]{10}$", message = "Phone number should include country code and be valid")
    @NotBlank(message = "Phone/Mobile is mandatory")
    @Schema(description = "Phone number of the User, including country code", example = "+11234567890")
    private String phone;

    @NotBlank(message = "Role is mandatory")
    @Pattern(regexp = "^(admin|user)$", message = "Role must be either 'admin' or 'user'")
    @Schema(description = "Role of the User", example = "user/admin")
    private String role;

    @Embedded
    @Schema(description = "Address of the User")
    private Address address;
}