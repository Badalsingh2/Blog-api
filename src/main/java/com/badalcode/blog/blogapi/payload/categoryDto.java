package com.badalcode.blog.blogapi.payload;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class categoryDto {

    private Integer categoryId;
    @NotBlank
    @Size(min=4)
    private String categoryTitle;
    @NotBlank
    @Size(min=19,message = "Min Size of Description is 19")
    private String categoryDescription;
}
