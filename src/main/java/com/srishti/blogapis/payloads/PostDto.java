package com.srishti.blogapis.payloads;

import java.util.Date;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class PostDto {

    private int id;

    private String title;

    @NotEmpty(message = "content can not be empty")
    private String content;

    private String image;

    private Date dateCreated;

    private CategoryDto category;

    private UserDto user;

}
