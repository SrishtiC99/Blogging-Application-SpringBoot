package com.srishti.blogapis.payloads;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class UserDto {
    
    private int id;

    @NotEmpty
    @Size(min = 4, message = "Username must be of length greater than 4")
    private String name;

    @Email(message = "Invalid email address")
    private String email;

    @NotEmpty
    @Size(min = 3, max = 10, message = "Password length must be minimum of 3 and maximum of 10")
    private String password;

    private String about;
}
