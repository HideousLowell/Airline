package ru.hlowell.airline.dto;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Data
public class UserDto {

    @Min(3)
    @NotBlank
    private String username;

    @Min(3)
    @NotBlank
    private String password;

    @Min(3)
    @NotBlank
    private String passwordConfirm;
}
