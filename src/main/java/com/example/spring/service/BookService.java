package com.example.spring.service;

import com.example.spring.dao.AuthorDao;
import com.example.spring.dao.BookDao;
import com.example.spring.entity.Author;
import com.example.spring.entity.Book;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class BookService {

    private final BookDao bookDao;

    private final AuthorDao authorDao;

    public List<Book> findAll() {
        return bookDao.findAll();
    }

    public Book findById(Integer id) {
        return bookDao.findById(id).orElse(null);
    }

    public void save(Book book) {
        bookDao.save(book);
    }

    public void update(Book book) {
        Book currentBook = bookDao.findById(book.getId()).orElse(null);
        if (currentBook != null) {
            if (book.getName() != null) {
                currentBook.setName(book.getName());
            }
            if (!book.getAuthor().isEmpty()) {
                currentBook.setAuthor(book.getAuthor());
            }
            bookDao.save(currentBook);
        }
    }

    @Transactional
    public void delete(Book book) {
        book = bookDao.findById(book.getId()).orElse(null);
        if (book != null) {
            List<Author> authors = book.getAuthor();
            for (Author author : authors) {
                author.getBooks().remove(book);
            }
            authorDao.saveAll(authors);

            bookDao.delete(book);
        }
    }
}
