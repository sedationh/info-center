package com.example.domin.vo;

import com.example.domin.entity.Tag;
import lombok.Data;

@Data
public class TagVo {
    private Long id;
    private String name;
    private String color;

    public TagVo(Tag tag) {
        this.id = tag.getId();
        this.name = tag.getName();
        this.color = tag.getColor();
    }
}