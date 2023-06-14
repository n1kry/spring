package com.example.spring.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@ToString
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ToString.Exclude
    private Integer id;

    @ToString.Include
    @Column(nullable = false)
    private String name;

    @ToString.Exclude
    @ManyToMany(mappedBy = "author", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Book> books;

    public Author() {
        books = new ArrayList<>();
    }

    public Author(Integer id, String name) {
        this.id = id;
        this.name = name;
        books = new ArrayList<>();
    }
}
