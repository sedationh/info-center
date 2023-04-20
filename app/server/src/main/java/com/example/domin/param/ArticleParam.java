package com.example.domin.param;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class ArticleParam {
    private Long id;
    @NotBlank
    private String title;
    @NotBlank
    private String content;
    @NotNull
    private Long[] tagIds;
}
