package com.srishti.blogapis.payloads;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class CategoryDto {
    
    private Integer categoryId;

    @NotEmpty(message = "Please provide a valid title")
    @Size(max = 50, message = "Title length must be less than 50")
    private String title;

    private String description;
}
