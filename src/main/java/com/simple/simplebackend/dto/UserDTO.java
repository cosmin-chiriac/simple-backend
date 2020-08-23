package com.simple.simplebackend.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
@ToString
public class UserDTO {


    private Integer id;

    @NotBlank(message = "A name must be provided.")
    private String name;

    @NotBlank (message = "An email must be provided.")
    @Email()
    private String email;

    @Min(value = 18, message = "Minimum age is 18")
    private Integer age;
}
