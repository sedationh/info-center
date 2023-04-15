package com.example.domin.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.Instant;
import java.util.List;

@Data
@Entity
@Table(name = "tags")
public class Tag {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "color")
    private String color;

    @Column(name = "created_at")
    private Instant createdAt;

    @Column(name = "updated_at")
    private Instant updatedAt;
}
