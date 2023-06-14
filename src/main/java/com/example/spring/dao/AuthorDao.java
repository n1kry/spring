package com.example.spring.dao;

import com.example.spring.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AuthorDao extends JpaRepository<Author, Integer> {
    List<Author> findByBooksName(String bookName);
}
