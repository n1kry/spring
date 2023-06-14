package com.example.spring;

import com.example.spring.config.AuthorConfig;
import com.example.spring.dao.AuthorDao;
import com.example.spring.entity.Author;
import com.example.spring.entity.Book;
import com.example.spring.service.AuthorService;
import com.example.spring.service.BookService;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@AllArgsConstructor
public class Application implements CommandLineRunner {
    private final AuthorService authorService;
    private final BookService bookService;
    private final Author author2;
    private final Author authorUpdate;
    private final Book bookUpdate;
    private final Book bookAdd;
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(String... args) {
        System.out.println(authorService.getAllAuthors());
        System.out.println(authorService.findAuthorsByBookName("Book 1"));
//        authorService.delete(author2);
//        bookService.delete(book1);
        bookService.update(bookUpdate);
        authorService.update(authorUpdate);
        System.out.println(authorService.getAuthorsWithBooks());
    }

}
