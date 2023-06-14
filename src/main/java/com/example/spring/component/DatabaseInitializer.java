package com.example.spring.component;

import com.example.spring.entity.Author;
import com.example.spring.entity.Book;
import com.example.spring.service.AuthorService;
import com.example.spring.service.BookService;
import lombok.AllArgsConstructor;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
    public class DatabaseInitializer implements ApplicationListener<ContextRefreshedEvent> {

    private final Author author1;
    private final Author author2;
    private final Author author3;
    private final Book book1;
    private final Book book2;
    private final Book book3;
    private final Book book4;

    private final AuthorService authorService;
    private final BookService bookService;

    @Override
    public void onApplicationEvent(@NonNull ContextRefreshedEvent event) {
        authorService.save(author1);
        authorService.save(author2);
        authorService.save(author3);

        book1.getAuthor().add(author1);
        book1.getAuthor().add(author2);
        bookService.save(book1);

        book2.getAuthor().add(author2);
        bookService.save(book2);

        book3.getAuthor().add(author1);
        bookService.save(book3);

        book4.getAuthor().add(author3);
        bookService.save(book4);
    }
}
