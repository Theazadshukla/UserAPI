package com.example.userapi.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
@Embeddable
@Schema(description = "Address of the User")
public class Address {

    @NotBlank(message = "House number is mandatory")
    @Schema(description = "House number of the User", example = "123")
    private String houseNumber;

    @NotBlank(message = "City is mandatory")
    @Schema(description = "City of the User", example = "Gurgaon")
    private String city;

    @NotBlank(message = "State is mandatory")
    @Schema(description = "State of the User", example = "HR")
    private String state;

    @NotBlank(message = "Country is mandatory")
    @Pattern(regexp = "^[A-Z]{2,3}$", message = "Country code should be valid")
    @Schema(description = "Country of the User", example = "IND")
    private String country;
}
