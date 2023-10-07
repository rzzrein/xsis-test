package com.movies.rest.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.*;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MovieDto {
    private long id;

    @NotEmpty
    @Size(min = 2, message = "'${validatedValue}' must have at least {min} characters")
    @Size(max = 10, message = "'${validatedValue}' exceeds the max limit of {max} characters")
    private String title;

    @NotEmpty
    @Size(min = 2, message = "'${validatedValue}' must have at least {min} characters")
    @Size(max = 20, message = "'${validatedValue}' exceeds the max limit of {max} characters")
    private String description;

    @Digits(integer = 1, fraction = 1)
    @NotNull
    @Min(value = 1)
    @Max(value = 10)
    private float rating;

    @Size(min = 2, message = "'${validatedValue}' must have at least {min} characters")
    @Size(max = 20, message = "'${validatedValue}' exceeds the max limit of {max} characters")
    private String image;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date created_at;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date updated_at;
}
