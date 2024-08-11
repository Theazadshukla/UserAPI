package com.example.userapi.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "Name is required")
    @Size(max = 50, message = "Name should not exceed 50 characters")
    private String name;

    @NotEmpty(message = "Email is required")
    @Email(message = "Invalid email format")
    @Column(unique = true)
    private String email;

    @NotEmpty(message = "Phone number is required")
    @Pattern(regexp = "^\\d{10}$", message = "Invalid phone number")
    @Column(unique = true)
    private String phone;

    @NotEmpty(message = "Role is required")
    @Pattern(regexp = "^(admin|user)$", message = "Role must be either 'admin' or 'user'")
    private String role;

    @NotEmpty(message = "House number is required")
    @Size(max = 10, message = "House number should not exceed 10 characters")
    private String houseNumber;

    @NotEmpty(message = "City is required")
    @Size(max = 50, message = "City name should not exceed 50 characters")
    private String city;

    @NotEmpty(message = "District is required")
    @Size(max = 50, message = "District name should not exceed 50 characters")
    private String district;

    @NotEmpty(message = "Pincode is required")
    @Pattern(regexp = "^\\d{5,6}$", message = "Invalid pincode")
    private String pincode;

    @NotEmpty(message = "Country is required")
    @Size(max = 50, message = "Country name should not exceed 50 characters")
    private String country;

    @NotEmpty(message = "Country code is required")
    @Pattern(regexp = "^[A-Z]{2,3}$", message = "Invalid country code")
    private String countryCode;

    // Constructors
    public User() {}

    // Getters and Setters

}

