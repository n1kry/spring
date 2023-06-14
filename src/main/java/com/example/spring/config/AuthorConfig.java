package com.example.spring.config;

import com.example.spring.entity.Author;
import com.example.spring.entity.Book;
import com.example.spring.service.AuthorService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class AuthorConfig {
    @Bean
    public Author author1() {
        Author author = new Author();
        author.setName("John Doe");
        return author;
    }

    @Bean
    public Author author2() {
        Author author = new Author();
        author.setName("Jane Smith");
        return author;
    }

    @Bean
    public Author author3() {
        Author author = new Author();
        author.setName("John Smith");
        return author;
    }

    @Bean
    public Author authorUpdate() {
        Author author = new Author();
        author.setId(2);
        author.setName("Git");
        return author;
    }

    @Bean
    public Book book1() {
        Book book = new Book();
        book.setName("Book 1");
        return book;
    }

    @Bean
    public Book book2() {
        Book book = new Book();
        book.setName("Book 2");
        return book;
    }

    @Bean
    public Book book3() {
        Book book = new Book();
        book.setName("Book 3");
        return book;
    }

    @Bean
    public Book book4() {
        Book book = new Book();
        book.setName("Book 4");
        return book;
    }

    @Bean
    public Book bookUpdate() {
        Book book = new Book();
        book.setId(1);
        book.setName("Updated Book 1");
        return book;
    }

    @Bean
    public Book bookAdd() {
        Book book = new Book();
        book.setName("Added Book 1");
        return book;
    }


}
