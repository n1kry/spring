package com.example.spring.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ToString.Exclude
    private Integer id;

    @ToString.Include
    private String name;

    @ToString.Exclude
    @ManyToMany
    private List<Author> author;

    public Book() {
        author = new ArrayList<>();
    }

    public Book(Integer id, String name) {
        this.id = id;
        this.name = name;
        author = new ArrayList<>();
    }
}