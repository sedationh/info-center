package com.example.domin.param;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class TagParams {
    private Long id;
    @NotBlank
    private String name;
    @NotBlank
    private String color;
}
